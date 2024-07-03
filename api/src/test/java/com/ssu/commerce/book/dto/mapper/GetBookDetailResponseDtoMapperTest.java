package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.response.GetBookDetailResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetBookDetailResponseDtoMapperTest {

    private GetBookDetailResponseDtoMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new GetBookDetailResponseDtoMapperImpl();
    }

    @Test
    void map_GivenValidBookDetailDto_ShouldReturnDtoWithSameValues() {
        BookDetailDto.BookDetailDtoBuilder bookDetailDtoBuilder = BookDetailDto.builder();
        bookDetailDtoBuilder.bookId(UUID.randomUUID());
        bookDetailDtoBuilder.title("Test Book");
        bookDetailDtoBuilder.content("This is a test book.");
        bookDetailDtoBuilder.comment("Destroyed Book");
        bookDetailDtoBuilder.writer("Author Name");
        bookDetailDtoBuilder.price(15l);
        bookDetailDtoBuilder.sharePrice(150l);
        bookDetailDtoBuilder.ownerId(UUID.randomUUID());
        bookDetailDtoBuilder.publishDate(LocalDate.now().atStartOfDay());
        bookDetailDtoBuilder.isbn("123-456-789");
        bookDetailDtoBuilder.startBorrowDay(LocalDateTime.parse("2024-05-13T15:30:00"));
        bookDetailDtoBuilder.endBorrowDay(LocalDateTime.parse("2025-05-13T15:30:00"));
        bookDetailDtoBuilder.categoryId(UUID.randomUUID());

        BookDetailDto bookDetailDto = bookDetailDtoBuilder.build();

        GetBookDetailResponseDto getBookDetailResponseDto = mapper.map(bookDetailDto);

        assertNotNull(getBookDetailResponseDto);
        assertEquals(getBookDetailResponseDto.getBookId(), bookDetailDto.getBookId());
        assertEquals(getBookDetailResponseDto.getTitle(), bookDetailDto.getTitle());
        assertEquals(getBookDetailResponseDto.getContent(), bookDetailDto.getContent());
        assertEquals(getBookDetailResponseDto.getComment(), bookDetailDto.getComment());
        assertEquals(getBookDetailResponseDto.getWriter(), bookDetailDto.getWriter());
        assertEquals(getBookDetailResponseDto.getPrice(), bookDetailDto.getPrice());
        assertEquals(getBookDetailResponseDto.getSharePrice(), bookDetailDto.getSharePrice());
        assertEquals(getBookDetailResponseDto.getOwnerId(), bookDetailDto.getOwnerId());
        assertEquals(getBookDetailResponseDto.getPublishDate(), bookDetailDto.getPublishDate());
        assertEquals(getBookDetailResponseDto.getIsbn(), bookDetailDto.getIsbn());
        assertEquals(getBookDetailResponseDto.getStartBorrowDay(), bookDetailDto.getStartBorrowDay());
        assertEquals(getBookDetailResponseDto.getEndBorrowDay(), bookDetailDto.getEndBorrowDay());
        assertEquals(getBookDetailResponseDto.getCategoryId(), bookDetailDto.getCategoryId());
    }

    @Test
    void map_GivenNullBook_ShouldReturnDtoWithNullValues() {
        GetBookDetailResponseDto getBookDetailResponseDto = mapper.map(null);

        assertNotNull(getBookDetailResponseDto);
        assertNull(getBookDetailResponseDto.getBookId());
        assertNull(getBookDetailResponseDto.getTitle());
        assertNull(getBookDetailResponseDto.getContent());
        assertNull(getBookDetailResponseDto.getComment());
        assertNull(getBookDetailResponseDto.getWriter());
        assertNull(getBookDetailResponseDto.getPrice());
        assertNull(getBookDetailResponseDto.getSharePrice());
        assertNull(getBookDetailResponseDto.getPublishDate());
        assertNull(getBookDetailResponseDto.getOwnerId());
        assertNull(getBookDetailResponseDto.getIsbn());
        assertNull(getBookDetailResponseDto.getStartBorrowDay());
        assertNull(getBookDetailResponseDto.getEndBorrowDay());
        assertNull(getBookDetailResponseDto.getCategoryId());
    }
}
