package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.dto.request.RegisterBookRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
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
        builder.writer("Author Name");
        builder.price(15l);
        builder.publishDate(LocalDate.now().atStartOfDay());
        builder.isbn("123-456-789");
        builder.maxBorrowDay(10l);
        builder.categoryId(UUID.randomUUID());

        RegisterBookRequestDto registerBookRequestDto = builder.build();

        RegisterBookParamDto registerBookParamDto = mapper.map(registerBookRequestDto);

        assertNotNull(registerBookParamDto);
        assertEquals(registerBookParamDto.getTitle(), registerBookRequestDto.getTitle());
        assertEquals(registerBookParamDto.getContent(), registerBookRequestDto.getContent());
        assertEquals(registerBookParamDto.getWriter(), registerBookRequestDto.getWriter());
        assertEquals(registerBookParamDto.getPrice(), registerBookRequestDto.getPrice());
        assertEquals(registerBookParamDto.getPublishDate(), registerBookRequestDto.getPublishDate());
        assertEquals(registerBookParamDto.getIsbn(), registerBookRequestDto.getIsbn());
        assertEquals(registerBookParamDto.getMaxBorrowDay(), registerBookRequestDto.getMaxBorrowDay());
        assertEquals(registerBookParamDto.getCategoryId(), registerBookRequestDto.getCategoryId());
    }

    @Test
    void map_GivenNull_ShouldReturnDtoWithNullValues() {
        RegisterBookParamDto dto = mapper.map(null);

        assertNotNull(dto);
        assertNull(dto.getTitle());
        assertNull(dto.getContent());
        assertNull(dto.getWriter());
        assertNull(dto.getPrice());
        assertNull(dto.getPublishDate());
        assertNull(dto.getIsbn());
        assertNull(dto.getMaxBorrowDay());
        assertNull(dto.getCategoryId());
    }
}
