package com.ssu.commerce.book.dto.param;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RegisterBookParamDto {
    @NotEmpty
    private String title;

    private String content;

    @NotEmpty
    private String writer;

    @NotNull
    private Long price;

    private LocalDateTime publishDate;

    @NotEmpty
    private String isbn;

    @NotNull
    private Long maxBorrowDay;

    @NotNull
    private Long categoryId;
}