package com.ssu.commerce.book.grpc;

import com.google.protobuf.Empty;
import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.exception.BookStateConflictException;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.book.supplier.BookTestDataSupplier;
import com.ssu.commerce.core.error.NotFoundException;
import com.ssu.commerce.grpc.UpdateBookStateRequest;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GrpcUpdateBookStateServiceTest implements BookTestDataSupplier {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private StreamObserver<Empty> response;

    @InjectMocks
    private GrpcUpdateBookStateService grpcUpdateBookStateService;

    @Test
     void updateBookTest_success() {
        when(bookRepository.findAllById(List.of(TEST_VAL_BOOK_ID, TEST_VAL_ANOTHER_BOOK_ID))).thenReturn(BookTestDataSupplier.getBookListForGrpc());
        UpdateBookStateRequest updateBookStateRequest = BookTestDataSupplier.getUpdateBookStateRequest();

        grpcUpdateBookStateService.updateBookState(updateBookStateRequest, response);

        verify(bookRepository).findAllById(List.of(TEST_VAL_BOOK_ID, TEST_VAL_ANOTHER_BOOK_ID));
        verify(bookRepository).save(Book.builder().bookId(TEST_VAL_BOOK_ID).bookState(BookState.SHARING).build());
        verify(bookRepository).save(Book.builder().bookId(TEST_VAL_ANOTHER_BOOK_ID).bookState(BookState.SHARING).build());
    }

    @Test
    void updateBookTest_fail_not_found() {
        UpdateBookStateRequest updateBookStateRequest = BookTestDataSupplier.getUpdateBookStateRequest();
        String expectedMessage = new NotFoundException(
                String.format("book not found; book list size=%d", 2),
                "BOOK_003"
        ).getMessage();

        when(bookRepository.findAllById(List.of(TEST_VAL_BOOK_ID, TEST_VAL_ANOTHER_BOOK_ID))).thenReturn(List.of());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> grpcUpdateBookStateService.updateBookState(updateBookStateRequest, response));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updateBookTest_fail_conflict() {
        when(bookRepository.findAllById(List.of(TEST_VAL_BOOK_ID, TEST_VAL_ANOTHER_BOOK_ID))).thenReturn(BookTestDataSupplier.getBookListForGrpcConflict());

        UpdateBookStateRequest updateBookStateRequest = BookTestDataSupplier.getUpdateBookStateRequest();

        BookStateConflictException expectedException = new BookStateConflictException("BOOK_004", "One or more books cannot be updated due to state conflicts.");
        BookStateConflictException exception = assertThrows(BookStateConflictException.class, () -> {
            grpcUpdateBookStateService.updateBookState(updateBookStateRequest, response);
        });

        assertEquals(expectedException, exception);
    }
}
