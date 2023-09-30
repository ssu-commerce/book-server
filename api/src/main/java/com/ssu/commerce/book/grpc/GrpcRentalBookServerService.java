package com.ssu.commerce.book.grpc;

import com.ssu.commerce.book.annotation.DistributedLock;
import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.dto.mapper.RentalBookRequestDtoMapper;
import com.ssu.commerce.book.dto.param.RentalBookRequestDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.core.exception.NotFoundException;
import com.ssu.commerce.order.grpc.CompleteRentalBookResponse;
import com.ssu.commerce.order.grpc.RentalBookGrpc;
import com.ssu.commerce.order.grpc.RentalBookRequest;
import com.ssu.commerce.order.grpc.RentalBookResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@GrpcService
@Slf4j
public class GrpcRentalBookServerService extends RentalBookGrpc.RentalBookImplBase {
    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public void rentalBook(RentalBookRequest request, StreamObserver<RentalBookResponse> responseObserver) {
        /*
            TODO RentalBookRequest 의 token 검증
         */

        @DistributedLock
        RentalBookRequestDto rentalBookRequestDto = RentalBookRequestDtoMapper.INSTANCE.map(request);

        UUID bookId = rentalBookRequestDto.getId();

        Book findBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("book not found; bookId=%s", bookId),
                        "BOOK_001"
                ));
        boolean LOAN_FINISHED;

        if (findBook.getBookState() == BookState.REGISTERED || findBook.getBookState() == BookState.RETURN) {
            findBook.updateBookState(BookState.LOAN_PROCESSING);
            LOAN_FINISHED = true;
        } else {
            LOAN_FINISHED = false;
        }
        responseObserver.onNext(
                RentalBookResponse.newBuilder()
                        .setRentalAvailabilityResponse(LOAN_FINISHED)
                        .build()
        );
        responseObserver.onCompleted();
    }
}
