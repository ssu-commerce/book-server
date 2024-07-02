package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.request.ChangeBookRequestDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ChangeBookParamDtoMapperTest {

    private ChangeBookParamDtoMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new ChangeBookParamDtoMapperImpl();
    }

    @Test
    void map_GivenValidChangeBookRequestDto_ShouldReturnDtoWithSameValues() {
        ChangeBookRequestDto.ChangeBookRequestDtoBuilder requestDtoBuilder = ChangeBookRequestDto.builder();
        requestDtoBuilder.bookId(UUID.randomUUID());
        requestDtoBuilder.title("Test Book");
        requestDtoBuilder.content("This is a test book.");
        requestDtoBuilder.comment("Destroyed Book");
        requestDtoBuilder.writer("Author Name");
        requestDtoBuilder.price(15l);
        requestDtoBuilder.price(150l);
        requestDtoBuilder.publishDate(LocalDate.now().atStartOfDay());
        requestDtoBuilder.isbn("123-456-789");
        requestDtoBuilder.startBorrowDay(LocalDateTime.parse("2024-05-13T15:30:00"));
        requestDtoBuilder.endBorrowDay(LocalDateTime.parse("2025-05-13T15:30:00"));
        requestDtoBuilder.categoryId(UUID.randomUUID());

        ChangeBookRequestDto requestDto = requestDtoBuilder.build();

        ChangeBookParamDto changeBookParamDto = mapper.map(requestDto);

        assertNotNull(changeBookParamDto);
        assertEquals(changeBookParamDto.getBookId(), requestDto.getBookId());
        assertEquals(changeBookParamDto.getTitle(), requestDto.getTitle());
        assertEquals(changeBookParamDto.getContent(), requestDto.getContent());
        assertEquals(changeBookParamDto.getComment(), requestDto.getComment());
        assertEquals(changeBookParamDto.getWriter(), requestDto.getWriter());
        assertEquals(changeBookParamDto.getPrice(), requestDto.getPrice());
        assertEquals(changeBookParamDto.getSharePrice(), requestDto.getSharePrice());
        assertEquals(changeBookParamDto.getPublishDate(), requestDto.getPublishDate());
        assertEquals(changeBookParamDto.getIsbn(), requestDto.getIsbn());
        assertEquals(changeBookParamDto.getStartBorrowDay(), requestDto.getStartBorrowDay());
        assertEquals(changeBookParamDto.getEndBorrowDay(), requestDto.getEndBorrowDay());
        assertEquals(changeBookParamDto.getCategoryId(), requestDto.getCategoryId());
    }

    @Test
    void map_GivenNullChangeBookRequestDto_ShouldReturnDtoWithNullValues() {
        ChangeBookParamDto changeBookParamDto = mapper.map(null);

        assertNotNull(changeBookParamDto);
        assertNull(changeBookParamDto.getBookId());
        assertNull(changeBookParamDto.getTitle());
        assertNull(changeBookParamDto.getContent());
        assertNull(changeBookParamDto.getComment());
        assertNull(changeBookParamDto.getWriter());
        assertNull(changeBookParamDto.getPrice());
        assertNull(changeBookParamDto.getSharePrice());
        assertNull(changeBookParamDto.getPublishDate());
        assertNull(changeBookParamDto.getIsbn());
        assertNull(changeBookParamDto.getStartBorrowDay());
        assertNull(changeBookParamDto.getEndBorrowDay());
        assertNull(changeBookParamDto.getCategoryId());
    }
}
