package com.ssu.commerce.book.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private Long price;
    private Long ownerId;
    private LocalDateTime publishDate;
    private String isbn;
    private Long maxBorrowDay;
    private Long categoryId;
}
