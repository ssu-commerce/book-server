package com.ssu.commerce.book.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class GetBookDetailResponseDto {
    private UUID id;
    private String title;
    private String content;
    private String writer;
    private Long price;
    private UUID ownerId;
    private LocalDateTime publishDate;
    private String isbn;
    private Long maxBorrowDay;
    private UUID categoryId;
}
