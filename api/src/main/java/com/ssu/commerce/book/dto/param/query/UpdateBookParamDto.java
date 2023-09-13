package com.ssu.commerce.book.dto.param.query;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UpdateBookParamDto {
    private UUID id;
    private String title;
    private String content;
    private Long price;
    private LocalDateTime publishDate;
    private String isbn;
    private Long maxBorrowDay;
    private UUID categoryId;
}
