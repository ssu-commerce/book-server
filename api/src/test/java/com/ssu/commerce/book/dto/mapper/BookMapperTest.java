package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.model.Book;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookMapperTest {

    private BookMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new BookMapperImpl();
    }

    @Test
    void map_GivenValidRegisterBookParamDto_ShouldReturnDtoWithSameValues() {
        RegisterBookParamDto.RegisterBookParamDtoBuilder builder = RegisterBookParamDto.builder();
        RegisterBookParamDto dto;

        builder.title("Test Book");
        builder.content("This is a test book.");
        builder.writer("Author Name");
        builder.price(15l);
        builder.publishDate(LocalDate.now().atStartOfDay());
        builder.isbn("123-456-789");
        builder.maxBorrowDay(10l);
        builder.categoryId(UUID.randomUUID());

        dto = builder.build();
        Book book = mapper.map(dto);

        assertNotNull(dto);
        assertEquals(book.getTitle(), dto.getTitle());
        assertEquals(book.getContent(), dto.getContent());
        assertEquals(book.getWriter(), dto.getWriter());
        assertEquals(book.getPrice(), dto.getPrice());
        assertEquals(book.getPublishDate(), dto.getPublishDate());
        assertEquals(book.getIsbn(), dto.getIsbn());
        assertEquals(book.getMaxBorrowDay(), dto.getMaxBorrowDay());
        assertEquals(book.getCategoryId(), dto.getCategoryId());
    }

    @Test
    void map_GivenNullBook_ShouldReturnDtoWithNullValues() {
        Book book = mapper.map(null);

        assertNotNull(book);
        assertNull(book.getId());
        assertNull(book.getTitle());
        assertNull(book.getContent());
        assertNull(book.getWriter());
        assertNull(book.getPrice());
        assertNull(book.getOwnerId());
        assertNull(book.getPublishDate());
        assertNull(book.getIsbn());
        assertNull(book.getMaxBorrowDay());
        assertNull(book.getCategoryId());
    }
}
