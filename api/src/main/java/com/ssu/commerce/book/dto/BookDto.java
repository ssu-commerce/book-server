package com.ssu.commerce.book.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode
public class BookDto {
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
