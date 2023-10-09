package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.dto.request.RegisterBookRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RegisterBookParamDtoMapperTest {

    private RegisterBookParamDtoMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new RegisterBookParamDtoMapperImpl();
    }

    @Test
    void map_GivenValidRegisterBookRequestDto_ShouldBeReturnRegisterBookParamDto() {
        RegisterBookRequestDto.RegisterBookRequestDtoBuilder builder = RegisterBookRequestDto.builder();

        builder.title("Test Book");
        builder.content("This is a test book.");
        builder.content("Desytroyed Book");
        builder.writer("Author Name");
        builder.price(15l);
        builder.sharePrice(150l);
        builder.publishDate(LocalDate.now().atStartOfDay());
        builder.isbn("123-456-789");
        builder.startBorrowDay(LocalDateTime.parse("2024-05-13T15:30:00"));
        builder.endBorrowDay(LocalDateTime.parse("2025-05-13T15:30:00"));
        builder.categoryId(UUID.randomUUID());

        RegisterBookRequestDto registerBookRequestDto = builder.build();

        RegisterBookParamDto registerBookParamDto = mapper.map(registerBookRequestDto);

        assertNotNull(registerBookParamDto);
        assertEquals(registerBookParamDto.getTitle(), registerBookRequestDto.getTitle());
        assertEquals(registerBookParamDto.getContent(), registerBookRequestDto.getContent());
        assertEquals(registerBookParamDto.getComment(), registerBookRequestDto.getComment());
        assertEquals(registerBookParamDto.getWriter(), registerBookRequestDto.getWriter());
        assertEquals(registerBookParamDto.getPrice(), registerBookRequestDto.getPrice());
        assertEquals(registerBookParamDto.getSharePrice(), registerBookRequestDto.getSharePrice());
        assertEquals(registerBookParamDto.getPublishDate(), registerBookRequestDto.getPublishDate());
        assertEquals(registerBookParamDto.getIsbn(), registerBookRequestDto.getIsbn());
        assertEquals(registerBookParamDto.getStartBorrowDay(), registerBookRequestDto.getStartBorrowDay());
        assertEquals(registerBookParamDto.getEndBorrowDay(), registerBookRequestDto.getEndBorrowDay());
        assertEquals(registerBookParamDto.getCategoryId(), registerBookRequestDto.getCategoryId());
    }

    @Test
    void map_GivenNull_ShouldReturnDtoWithNullValues() {
        RegisterBookParamDto dto = mapper.map(null);

        assertNotNull(dto);
        assertNull(dto.getTitle());
        assertNull(dto.getContent());
        assertNull(dto.getComment());
        assertNull(dto.getWriter());
        assertNull(dto.getPrice());
        assertNull(dto.getSharePrice());
        assertNull(dto.getPublishDate());
        assertNull(dto.getIsbn());
        assertNull(dto.getStartBorrowDay());
        assertNull(dto.getEndBorrowDay());
        assertNull(dto.getCategoryId());
    }
}
