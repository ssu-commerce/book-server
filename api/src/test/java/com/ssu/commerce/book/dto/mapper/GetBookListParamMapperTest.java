package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetBookListParamMapperTest {

    private GetBookListParamMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new GetBookListParamMapperImpl();
    }

    @Test
    void map_GivenValidTitleCategoryIdPageable_ShouldBeReturnGetBookListParamDto() {
        String title = "Test Book";
        UUID CategoryId = UUID.randomUUID();
        Pageable pageable = Pageable.unpaged();

        GetBookListParamDto getBookListParamDto = mapper.map(title, CategoryId, pageable);

        assertNotNull(getBookListParamDto);
        assertEquals(getBookListParamDto.getTitle(), title);
        assertEquals(getBookListParamDto.getCategoryId(), CategoryId);
        assertEquals(getBookListParamDto.getPageable(), pageable);
    }

    @Test
    void map_GivenNullTitleCategoryIdPageable_ShouldReturnDtoWithNullValues() {
        String title = null;
        UUID CategoryId = null;
        Pageable pageable = null;

        GetBookListParamDto getBookListParamDto = mapper.map(title, CategoryId, pageable);

        assertNotNull(getBookListParamDto);
        assertNull(getBookListParamDto.getTitle());
        assertNull(getBookListParamDto.getCategoryId());
        assertNull(getBookListParamDto.getPageable());
    }
}
