package com.ssu.commerce.book.grpc;

import com.ssu.commerce.book.annotation.DistributedLock;
import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.dto.param.CompleteRentalBookRequestDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.core.error.NotFoundException;
import com.ssu.commerce.order.grpc.CompleteRentalBookGrpc;
import com.ssu.commerce.order.grpc.CompleteRentalBookRequest;
import com.ssu.commerce.order.grpc.CompleteRentalBookResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GrpcService
@Slf4j
public class GrpcCompleteRentalBookServerService extends CompleteRentalBookGrpc.CompleteRentalBookImplBase {

    @Autowired
    private BookRepository bookRepository;


    @Override
    @Transactional
    public void completeRentalBook(CompleteRentalBookRequest request, StreamObserver<CompleteRentalBookResponse> responseObserver) {

        List<CompleteRentalBookRequestDto> completeRentalBookRequestDto  = request.getIdList()
                .stream().map(id ->
                        UUID.fromString(id)
                ).collect(Collectors.toList())
                .stream().map(id ->
                        CompleteRentalBookRequestDto.builder().id(id).build()
                ).collect(Collectors.toList());

        completeUpdateBookState(completeRentalBookRequestDto, responseObserver);
    }

    public void completeUpdateBookState(@DistributedLock List<CompleteRentalBookRequestDto> requestDto, StreamObserver<CompleteRentalBookResponse> responseObserver) {
        List<UUID> bookId = requestDto.stream().map(CompleteRentalBookRequestDto::getId).collect(Collectors.toList());

        List<Book> findBook = bookRepository.findAllById(bookId);

        if (findBook.isEmpty()) {
            throw new NotFoundException(
                    String.format("book not found; book list size=%d", bookId.size()),
                    "BOOK_003"
            );
        }

        for (Book book : findBook) {
            book.updateBookState(BookState.LOAN);
        }

        responseObserver.onNext(
                CompleteRentalBookResponse.newBuilder()
                        .setCompleteRentalResponse(true)
                        .build()
        );
        responseObserver.onCompleted();
    }
}
