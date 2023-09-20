package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.response.GetBookResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetBookResponseDtoMapperTest {

    private GetBookResponseDtoMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new GetBookResponseDtoMapperImpl();
    }

    @Test
    void map_GivenValidBookDto_ShouldBeReturnGetBookResponseDto() {
        BookDto.BookDtoBuilder builder = BookDto.builder();

        builder.id(UUID.randomUUID());
        builder.title("Test Book");
        builder.content("This is a test book.");
        builder.writer("Author Name");
        builder.price(15l);
        builder.ownerId(UUID.randomUUID());
        builder.publishDate(LocalDate.now().atStartOfDay());
        builder.isbn("123-456-789");
        builder.maxBorrowDay(10l);
        builder.categoryId(UUID.randomUUID());

        BookDto bookDto = builder.build();

        GetBookResponseDto getBookResponseDto = mapper.map(bookDto);

        assertNotNull(getBookResponseDto);
        assertEquals(getBookResponseDto.getId(), bookDto.getId());
        assertEquals(getBookResponseDto.getTitle(), bookDto.getTitle());
        assertEquals(getBookResponseDto.getContent(), bookDto.getContent());
        assertEquals(getBookResponseDto.getWriter(), bookDto.getWriter());
        assertEquals(getBookResponseDto.getPrice(), bookDto.getPrice());
        assertEquals(getBookResponseDto.getOwnerId(), bookDto.getOwnerId());
        assertEquals(getBookResponseDto.getPublishDate(), bookDto.getPublishDate());
        assertEquals(getBookResponseDto.getIsbn(), bookDto.getIsbn());
        assertEquals(getBookResponseDto.getMaxBorrowDay(), bookDto.getMaxBorrowDay());
        assertEquals(getBookResponseDto.getCategoryId(), bookDto.getCategoryId());
    }

    @Test
    void map_GivenNull_ShouldReturnDtoWithNullValues() {
        GetBookResponseDto dto = mapper.map(null);

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
