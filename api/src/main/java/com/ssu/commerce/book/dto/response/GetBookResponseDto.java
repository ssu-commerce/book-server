package com.ssu.commerce.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBookResponseDto {
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
