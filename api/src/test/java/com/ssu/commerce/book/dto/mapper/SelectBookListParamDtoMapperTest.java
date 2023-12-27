package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.dto.param.query.SelectBookListParamDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SelectBookListParamDtoMapperTest {

    private SelectBookListParamDtoMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new SelectBookListParamDtoMapperImpl();
    }

    @Test
    void map_GivenValidGetBookListParamDto_ShouldBeReturnSelectBookListParamDto() {
        GetBookListParamDto.GetBookListParamDtoBuilder builder = GetBookListParamDto.builder();

        builder.title("Test Book");
        builder.categoryId(UUID.randomUUID());
        builder.pageable(Pageable.unpaged());

        GetBookListParamDto getBookListParamDto = builder.build();

        SelectBookListParamDto getBookResponseDto = mapper.map(getBookListParamDto);

        assertNotNull(getBookResponseDto);
        assertEquals(getBookResponseDto.getTitle(), getBookListParamDto.getTitle());
        assertEquals(getBookResponseDto.getCategoryId(), getBookListParamDto.getCategoryId());
    }

    @Test
    void map_GivenNull_ShouldReturnDtoWithNullValues() {
        SelectBookListParamDto dto = mapper.map(null);

        assertNotNull(dto);
        assertNull(dto.getTitle());
        assertNull(dto.getCategoryId());
    }
}
