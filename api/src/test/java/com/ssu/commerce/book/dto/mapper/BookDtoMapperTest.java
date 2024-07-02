package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.model.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookDtoMapperTest {

    private BookDtoMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new BookDtoMapperImpl();
    }

    @Test
    void map_GivenValidBook_ShouldReturnDtoWithSameValues() {
        Book.BookBuilder builder = Book.builder();
        Book book;

        builder.bookId(UUID.randomUUID());
        builder.title("Test Book");
        builder.content("This is a test book.");
        builder.content("Desytroyed Book");
        builder.writer("Author Name");
        builder.price(15l);
        builder.sharePrice(150l);
        builder.ownerId(UUID.randomUUID());
        builder.publishDate(LocalDate.now().atStartOfDay());
        builder.isbn("123-456-789");
        builder.startBorrowDay(LocalDateTime.parse("2024-05-13T15:30:00"));
        builder.endBorrowDay(LocalDateTime.parse("2025-05-13T15:30:00"));
        builder.categoryId(UUID.randomUUID());

        book = builder.build();

        BookDto dto = mapper.map(book);

        assertNotNull(dto);
        assertEquals(book.getBookId(), dto.getBookId());
        assertEquals(book.getTitle(), dto.getTitle());
        assertEquals(book.getContent(), dto.getContent());
        assertEquals(book.getComment(), dto.getComment());
        assertEquals(book.getWriter(), dto.getWriter());
        assertEquals(book.getSharePrice(), dto.getSharePrice());
        assertEquals(book.getPrice(), dto.getPrice());
        assertEquals(book.getOwnerId(), dto.getOwnerId());
        assertEquals(book.getPublishDate(), dto.getPublishDate());
        assertEquals(book.getIsbn(), dto.getIsbn());
        assertEquals(book.getStartBorrowDay(), dto.getStartBorrowDay());
        assertEquals(book.getEndBorrowDay(), dto.getEndBorrowDay());
        assertEquals(book.getCategoryId(), dto.getCategoryId());
    }

    @Test
    void map_GivenNullBook_ShouldReturnDtoWithNullValues() {
        BookDto dto = mapper.map(null);

        assertNotNull(dto);
        assertNull(dto.getBookId());
        assertNull(dto.getTitle());
        assertNull(dto.getContent());
        assertNull(dto.getComment());
        assertNull(dto.getWriter());
        assertNull(dto.getPrice());
        assertNull(dto.getSharePrice());
        assertNull(dto.getOwnerId());
        assertNull(dto.getPublishDate());
        assertNull(dto.getIsbn());
        assertNull(dto.getStartBorrowDay());
        assertNull(dto.getEndBorrowDay());
        assertNull(dto.getCategoryId());
    }
}
