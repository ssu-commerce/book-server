package com.ssu.commerce.book.grpc;

import com.ssu.commerce.book.annotation.DistributedLock;
import com.ssu.commerce.book.dto.param.RentalBookRequestDto;
import com.ssu.commerce.book.exception.BookStateConflictException;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.core.error.NotFoundException;
import com.ssu.commerce.order.grpc.UpdateBookStateRequest;
import com.ssu.commerce.order.grpc.UpdateBookStateResponse;
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
public class GrpcUpdateBookStateService {
    private final BookRepository bookRepository;

    @Transactional
    public void updateBookState(
            UpdateBookStateRequest request,
            StreamObserver<UpdateBookStateResponse> responseObserver
    ) {
        /*
         *   TODO RentalBookRequest 의 token 검증
         */

        List<RentalBookRequestDto> rentalBookRequestDto = request.getIdList()
                .stream().map(id ->
                        UUID.fromString(id)
                ).collect(Collectors.toList())
                .stream().map(id ->
                        RentalBookRequestDto.builder().id(id).build()
                ).collect(Collectors.toList());

        updateBookState(rentalBookRequestDto, responseObserver);

    }

    public void updateBookState(
            @DistributedLock List<RentalBookRequestDto> requestDto,
            StreamObserver<UpdateBookStateResponse> responseObserver
    ) {
        List<UUID> bookId = requestDto.stream().map(RentalBookRequestDto::getId).collect(Collectors.toList());

        List<Book> findBook = bookRepository.findAllById(bookId);

        if (findBook.isEmpty()) {
            throw new NotFoundException(
                    String.format("book not found; book list size=%d", bookId.size()),
                    "BOOK_003"
            );
        }

        for (Book book : findBook) {
            if (!book.rental()) {
                throw new BookStateConflictException("BOOK_004", String.format("Book State Conflict; BookID=%s, BookState=%s",book.getId(), book.getBookState()));
            }
        }

        responseObserver.onNext(
                UpdateBookStateResponse.newBuilder()
                        .build()
        );
        responseObserver.onCompleted();
    }
}
