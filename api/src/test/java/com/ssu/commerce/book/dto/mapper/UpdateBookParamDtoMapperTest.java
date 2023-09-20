package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.param.query.UpdateBookParamDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UpdateBookParamDtoMapperTest {

    private UpdateBookParamDtoMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new UpdateBookParamDtoMapperImpl();
    }

    @Test
    void map_GivenValidChangeBookParamDto_ShouldBeReturnUpdateBookParamDto() {
        ChangeBookParamDto.ChangeBookParamDtoBuilder builder = ChangeBookParamDto.builder();

        builder.id(UUID.randomUUID());
        builder.title("Test Book");
        builder.content("This is a test book.");
        builder.writer("Author Name");
        builder.price(15l);
        builder.publishDate(LocalDate.now().atStartOfDay());
        builder.isbn("123-456-789");
        builder.maxBorrowDay(10l);
        builder.categoryId(UUID.randomUUID());

        ChangeBookParamDto changeBookParamDto = builder.build();

        UpdateBookParamDto updateBookParamDto = mapper.map(changeBookParamDto);

        assertNotNull(updateBookParamDto);
        assertEquals(updateBookParamDto.getId(), changeBookParamDto.getId());
        assertEquals(updateBookParamDto.getTitle(), changeBookParamDto.getTitle());
        assertEquals(updateBookParamDto.getContent(), changeBookParamDto.getContent());
        assertEquals(updateBookParamDto.getPrice(), changeBookParamDto.getPrice());
        assertEquals(updateBookParamDto.getPublishDate(), changeBookParamDto.getPublishDate());
        assertEquals(updateBookParamDto.getIsbn(), changeBookParamDto.getIsbn());
        assertEquals(updateBookParamDto.getMaxBorrowDay(), changeBookParamDto.getMaxBorrowDay());
        assertEquals(updateBookParamDto.getCategoryId(), changeBookParamDto.getCategoryId());
    }

    @Test
    void map_GivenNull_ShouldReturnDtoWithNullValues() {
        UpdateBookParamDto dto = mapper.map(null);

        assertNotNull(dto);
        assertNull(dto.getId());
        assertNull(dto.getTitle());
        assertNull(dto.getContent());
        assertNull(dto.getPrice());
        assertNull(dto.getPublishDate());
        assertNull(dto.getIsbn());
        assertNull(dto.getMaxBorrowDay());
        assertNull(dto.getCategoryId());
    }
}
