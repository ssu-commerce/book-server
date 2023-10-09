package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.model.Book;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookDetailDtoMapperTest {

    private BookDetailDtoMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new BookDetailDtoMapperImpl();
    }

    @Test
    void map_GivenValidBookDetail_ShouldReturnDtoWithSameValues() {
        Book.BookBuilder builder = Book.builder();
        Book book;

        builder.id(UUID.randomUUID());
        builder.title("Test Book");
        builder.content("This is a test book.");
        builder.writer("Author Name");
        builder.price(15l);
        builder.ownerId(UUID.randomUUID());
        builder.publishDate(LocalDate.now().atStartOfDay());
        builder.isbn("123-456-789");
        builder.maxBorrowDay(10l);
        builder.categoryId(UUID.randomUUID());

        book = builder.build();

        BookDetailDto dto = mapper.map(book);

        assertNotNull(dto);
        assertEquals(book.getId(), dto.getId());
        assertEquals(book.getTitle(), dto.getTitle());
        assertEquals(book.getContent(), dto.getContent());
        assertEquals(book.getWriter(), dto.getWriter());
        assertEquals(book.getPrice(), dto.getPrice());
        assertEquals(book.getOwnerId(), dto.getOwnerId());
        assertEquals(book.getPublishDate(), dto.getPublishDate());
        assertEquals(book.getIsbn(), dto.getIsbn());
        assertEquals(book.getMaxBorrowDay(), dto.getMaxBorrowDay());
        assertEquals(book.getCategoryId(), dto.getCategoryId());
    }

    @Test
    void map_GivenNullBookDetail_ShouldReturnDtoWithNullValues() {
        BookDetailDto dto = mapper.map(null);

        assertNotNull(dto);
        assertNull(dto.getId());
        assertNull(dto.getTitle());
        assertNull(dto.getContent());
        assertNull(dto.getWriter());
        assertNull(dto.getPrice());
        assertNull(dto.getOwnerId());
        assertNull(dto.getPublishDate());
        assertNull(dto.getIsbn());
        assertNull(dto.getMaxBorrowDay());
        assertNull(dto.getCategoryId());
    }
}
