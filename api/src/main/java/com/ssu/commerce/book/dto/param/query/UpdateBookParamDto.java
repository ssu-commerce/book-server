package com.ssu.commerce.book.dto.param.query;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder
public class UpdateBookParamDto {
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
