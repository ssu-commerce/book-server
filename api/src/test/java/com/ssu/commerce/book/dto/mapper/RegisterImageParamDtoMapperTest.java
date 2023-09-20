package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.RegisterImageParamDto;
import com.ssu.commerce.book.dto.request.RegisterImageRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RegisterImageParamDtoMapperTest {

    private RegisterImageParamDtoMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new RegisterImageParamDtoMapperImpl();
    }

    @Test
    void map_GivenValidRegisterImageRequestDto_ShouldBeReturnRegisterImageParamDto() {
        RegisterImageRequestDto.RegisterImageRequestDtoBuilder builder = RegisterImageRequestDto.builder();

        builder.bookId(UUID.randomUUID());

        RegisterImageRequestDto registerImageRequestDto = builder.build();

        RegisterImageParamDto registerImageParamDto = mapper.map(registerImageRequestDto);

        assertNotNull(registerImageParamDto);
        assertEquals(registerImageParamDto.getBookId(), registerImageRequestDto.getBookId());
    }

    @Test
    void map_GivenNull_ShouldReturnDtoWithNullValues() {
        RegisterImageParamDto dto = mapper.map(null);

        assertNotNull(dto);
        assertNull(dto.getBookId());
    }
}
