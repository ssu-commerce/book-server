package com.ssu.commerce.book.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookRequestDto {
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