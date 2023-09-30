package com.ssu.commerce.book.grpc;

import com.ssu.commerce.book.annotation.DistributedLock;
import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.dto.mapper.RollBackBookRequestDtoMapper;
import com.ssu.commerce.book.dto.param.RollBackBookRequestDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.core.exception.NotFoundException;
import com.ssu.commerce.order.grpc.CompleteRentalBookResponse;
import com.ssu.commerce.order.grpc.RollBackBookRequest;
import com.ssu.commerce.order.grpc.RollBackBookResponse;
import com.ssu.commerce.order.grpc.RollBackRentalGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@GrpcService
public class GrpcRollBackBookService extends RollBackRentalGrpc.RollBackRentalImplBase {

    @Autowired
    private BookRepository bookRepository;


    @Override
    @Transactional
    public void rollBackRental(RollBackBookRequest request, StreamObserver<RollBackBookResponse> responseObserver) {

        @DistributedLock
        RollBackBookRequestDto rollBackBookRequestDto = RollBackBookRequestDtoMapper.INSTANCE.map(request);
        UUID bookId = rollBackBookRequestDto.getId();
        Book findBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("book not found; bookId=%s", bookId),
                        "BOOK_001"
                ));
        findBook.updateBookState(BookState.REGISTERED);
        responseObserver.onNext(
                RollBackBookResponse.newBuilder()
                        .setRollBackBookResponse(findBook.rollBack())
                        .build()
        );
        responseObserver.onCompleted();
    }
}
