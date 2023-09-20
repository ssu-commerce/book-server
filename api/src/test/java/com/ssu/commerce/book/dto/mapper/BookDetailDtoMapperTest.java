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
        Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setTitle("Test Book");
        book.setContent("This is a test book.");
        book.setWriter("Author Name");
        book.setPrice(15l);
        book.setOwnerId(UUID.randomUUID());
        book.setPublishDate(LocalDate.now().atStartOfDay());
        book.setIsbn("123-456-789");
        book.setMaxBorrowDay(10l);
        book.setCategoryId(UUID.randomUUID());

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
