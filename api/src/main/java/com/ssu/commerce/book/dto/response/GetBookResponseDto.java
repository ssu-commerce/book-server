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
    private UUID bookId;
    private String title;
    private String content;
    private String writer;
    private Long price;
    private Long sharePrice;
    private String comment;
    private LocalDateTime startBorrowDay;
    private LocalDateTime endBorrowDay;
    private UUID ownerId;
    private LocalDateTime publishDate;
    private String isbn;
    private UUID categoryId;
}
