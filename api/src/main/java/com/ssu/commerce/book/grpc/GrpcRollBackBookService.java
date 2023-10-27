package com.ssu.commerce.book.grpc;

import com.ssu.commerce.book.annotation.DistributedLock;
import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.dto.param.RollBackBookRequestDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.core.exception.NotFoundException;
import com.ssu.commerce.order.grpc.RollBackBookRequest;
import com.ssu.commerce.order.grpc.RollBackBookResponse;
import com.ssu.commerce.order.grpc.RollBackRentalGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GrpcService
public class GrpcRollBackBookService extends RollBackRentalGrpc.RollBackRentalImplBase {

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public void rollBackRental(RollBackBookRequest request, StreamObserver<RollBackBookResponse> responseObserver) {

        List<RollBackBookRequestDto> rollBackBookRequestDto = request.getIdList()
                .stream().map(id ->
                        UUID.fromString(id)
                ).collect(Collectors.toList())
                .stream().map(id -> RollBackBookRequestDto.builder().id(id).build()
                ).collect(Collectors.toList());

        rollBackBookState(rollBackBookRequestDto, responseObserver);
    }

    public void rollBackBookState(@DistributedLock List<RollBackBookRequestDto> rollBackBookRequestDto,StreamObserver<RollBackBookResponse> responseObserver) {
        List<UUID> bookId = rollBackBookRequestDto.stream().map(RollBackBookRequestDto::getId).collect(Collectors.toList());

        List<Book> findBook = bookRepository.findAllById(bookId);

        if (findBook.isEmpty()) {
            throw new NotFoundException(
                    String.format("book not found; book list size=%d", bookId.size()),
                    "BOOK_003"
            );
        }

        for (Book book : findBook) {
            book.updateBookState(BookState.REGISTERED);
        }


        responseObserver.onNext(
                RollBackBookResponse.newBuilder()
                        .setRollBackBookResponse(true)
                        .build()
        );
        responseObserver.onCompleted();
    }
}
