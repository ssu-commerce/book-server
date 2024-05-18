package com.ssu.commerce.book.grpc;

import com.ssu.commerce.book.config.QuerydslConfig;
import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.book.supplier.BookTestDataSupplier;
import com.ssu.commerce.core.jpa.config.JpaConfig;
import com.ssu.commerce.grpc.UpdateBookStateRequest;
import com.ssu.commerce.grpc.UpdateBookStateResponse;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@Import({JpaConfig.class, QuerydslConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class GrpcUpdateBookStateServiceTest {

    @MockBean
    private RedissonClient redissonClient;

    @Mock
    private RLock mockLock;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private StreamObserver<UpdateBookStateResponse> responseObserver;

    @InjectMocks
    private GrpcUpdateBookStateService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(redissonClient.getLock(Mockito.anyString())).thenReturn(mockLock);

        Mockito.doNothing().when(mockLock).lock();
        Mockito.doNothing().when(mockLock).unlock();
    }

    @Test
    public void shouldUpdateBookStateWhenAllBooksAreUpdatable() {
        List<UUID> ids = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());

        List<Book> books = ids.stream()
                .map(id -> {
                    Book book = BookTestDataSupplier.getBookWithState();
                    book.setId(id);
                    return book;
                })
                .collect(Collectors.toList());

        when(bookRepository.findAllById(any())).thenReturn(books);

        List<Book> returnedBooks = bookRepository.findAllById(ids);

        assertEquals(books, returnedBooks);

        UpdateBookStateRequest.Builder requestBuilder = UpdateBookStateRequest.newBuilder()
                .setBookState(com.ssu.commerce.grpc.BookState.SHARING);

        books.forEach(book -> requestBuilder.addId(book.getId().toString()));

        UpdateBookStateRequest request = requestBuilder.build();

        service.updateBookState(request, responseObserver);

        verify(bookRepository, times(2)).save(any(Book.class));
        verify(responseObserver, times(1)).onNext(any());
        verify(responseObserver, times(1)).onCompleted();
    }
}
