package com.ssu.commerce.book.grpc;

import com.google.protobuf.Empty;
import com.ssu.commerce.book.annotation.DistributedLock;
import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.dto.mapper.GrpcBookStateMapper;
import com.ssu.commerce.book.dto.param.RentalBookRequestDto;
import com.ssu.commerce.book.exception.BookStateConflictException;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.core.error.NotFoundException;
import com.ssu.commerce.grpc.RentalBookRequest;
import com.ssu.commerce.grpc.RentalBookResponse;
import com.ssu.commerce.grpc.UpdateBookStateGrpc;
import com.ssu.commerce.grpc.UpdateBookStateRequest;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class GrpcUpdateBookStateService extends UpdateBookStateGrpc.UpdateBookStateImplBase {
    private final BookRepository bookRepository;

    @Override
    public void rentalBook(RentalBookRequest request, StreamObserver<RentalBookResponse> responseObserver) {
        List<RentalBookRequestDto> rentalBookRequestDto = request.getIdList()
                .stream().map(id -> RentalBookRequestDto.builder().bookId(UUID.fromString(id)).build())
                .collect(Collectors.toList());

        try {
            if (processUpdateBookState(rentalBookRequestDto, BookState.SHARING)) {
                RentalBookResponse response = RentalBookResponse.newBuilder()
                        .setMessage("Books rented successfully.")
                        .build();
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            RentalBookResponse response = RentalBookResponse.newBuilder()
                    .setMessage("Books not found.")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            throw e;
        } catch (BookStateConflictException e) {
            RentalBookResponse response = RentalBookResponse.newBuilder()
                    .setMessage("Books could not be rented due to state conflicts.")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            throw e;
        }
    }

    @Override
    @Transactional
    public void updateBookState(
            UpdateBookStateRequest request,
            StreamObserver<Empty> responseObserver
    ) {
        /*
         *   TODO RentalBookRequest 의 token 검증
         */

        com.ssu.commerce.grpc.BookState updateState = request.getBookState();

        List<RentalBookRequestDto> rentalBookRequestDto = request.getIdList()
                .stream().map(id -> RentalBookRequestDto.builder().bookId(UUID.fromString(id)).build())
                .collect(Collectors.toList());

        com.ssu.commerce.book.constant.code.BookState state = GrpcBookStateMapper.INSTANCE.map(updateState);

        processUpdateBookState(rentalBookRequestDto, state);
        responseObserver.onCompleted();
    }

    private boolean processUpdateBookState(
            @DistributedLock List<RentalBookRequestDto> requestDto,
            com.ssu.commerce.book.constant.code.BookState updateState
    ) {
        List<UUID> bookIds = requestDto.stream().map(RentalBookRequestDto::getBookId).collect(Collectors.toList());
        List<Book> booksToCheck = bookRepository.findAllById(bookIds);

        if (booksToCheck.isEmpty()) {
            throw new NotFoundException(
                    String.format("book not found; book list size=%d", bookIds.size()),
                    "BOOK_003"
            );
        }

        boolean allUpdatePossible = booksToCheck.stream()
                .allMatch(book -> checkUpdateBookState(book, updateState));

        if (!allUpdatePossible) {
            throw new BookStateConflictException("BOOK_004", "One or more books cannot be updated due to state conflicts.");
        }

        booksToCheck.forEach(book -> {
            book.updateBookState(updateState);  // 가정: setBookState 메소드는 책의 상태를 업데이트
            bookRepository.save(book);  // 변경된 상태를 저장
        });

        return true;
    }

    private boolean checkUpdateBookState(Book book, BookState bookState) {
        // 요청된 상태가 SHARING일 때는 현재 책 상태가 SHARING 또는 DISSHAREABLE이면 변경할 수 없음
        if (bookState == BookState.SHARING) {
            return !(book.getBookState() == BookState.SHARING || book.getBookState() == BookState.DISSHAREABLE);
        }
        // 요청된 상태가 SHARABLE일 때는 disshrable이 아닐때 가능
        else if (bookState == BookState.SHARABLE) {
            return !(book.getBookState() == BookState.DISSHAREABLE);
        }

        // dissharable이 grpc를 통해 요청으로 들어올 일이 있는지 의문
        return false;
    }
}
