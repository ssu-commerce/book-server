package com.ssu.commerce.book.grpc;

import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.core.exception.NotFoundException;
import com.ssu.commerce.order.grpc.CompleteRentalBookGrpc;
import com.ssu.commerce.order.grpc.CompleteRentalBookRequest;
import com.ssu.commerce.order.grpc.CompleteRentalBookResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@GrpcService
public class GrpcCompleteRentalBookServerService extends CompleteRentalBookGrpc.CompleteRentalBookImplBase {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public void completeRentalBook(CompleteRentalBookRequest request, StreamObserver<CompleteRentalBookResponse> responseObserver) {
        UUID bookId = UUID.fromString(request.getId());
        Book findBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("book not found; bookId=%s", bookId),
                        "BOOK_001"
                ));
        findBook.updateBookState(BookState.LOAN);
        responseObserver.onNext(
                CompleteRentalBookResponse.newBuilder()
                        .setCompleteRentalResponse(true)
                        .build()
        );
        responseObserver.onCompleted();
    }
}
