package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.request.ChangeBookRequestDto;
import org.junit.jupiter.api.Test;
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
        requestDtoBuilder.id(UUID.randomUUID());
        requestDtoBuilder.title("Test Book");
        requestDtoBuilder.content("This is a test book.");
        requestDtoBuilder.writer("Author Name");
        requestDtoBuilder.price(15l);
        requestDtoBuilder.publishDate(LocalDate.now().atStartOfDay());
        requestDtoBuilder.isbn("123-456-789");
        requestDtoBuilder.maxBorrowDay(10l);
        requestDtoBuilder.categoryId(UUID.randomUUID());

        ChangeBookRequestDto requestDto = requestDtoBuilder.build();

        ChangeBookParamDto changeBookParamDto = mapper.map(requestDto);

        assertNotNull(changeBookParamDto);
        assertEquals(changeBookParamDto.getId(), requestDto.getId());
        assertEquals(changeBookParamDto.getTitle(), requestDto.getTitle());
        assertEquals(changeBookParamDto.getContent(), requestDto.getContent());
        assertEquals(changeBookParamDto.getWriter(), requestDto.getWriter());
        assertEquals(changeBookParamDto.getPrice(), requestDto.getPrice());
        assertEquals(changeBookParamDto.getPublishDate(), requestDto.getPublishDate());
        assertEquals(changeBookParamDto.getIsbn(), requestDto.getIsbn());
        assertEquals(changeBookParamDto.getMaxBorrowDay(), requestDto.getMaxBorrowDay());
        assertEquals(changeBookParamDto.getCategoryId(), requestDto.getCategoryId());
    }

    @Test
    void map_GivenNullChangeBookRequestDto_ShouldReturnDtoWithNullValues() {
        ChangeBookParamDto changeBookParamDto = mapper.map(null);

        assertNotNull(changeBookParamDto);
        assertNull(changeBookParamDto.getId());
        assertNull(changeBookParamDto.getTitle());
        assertNull(changeBookParamDto.getContent());
        assertNull(changeBookParamDto.getWriter());
        assertNull(changeBookParamDto.getPrice());
        assertNull(changeBookParamDto.getPublishDate());
        assertNull(changeBookParamDto.getIsbn());
        assertNull(changeBookParamDto.getMaxBorrowDay());
        assertNull(changeBookParamDto.getCategoryId());
    }
}
