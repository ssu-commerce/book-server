package com.ssu.commerce.book.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
public class BookDto {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) &&
                Objects.equals(title, bookDto.title) &&
                Objects.equals(content, bookDto.content) &&
                Objects.equals(writer, bookDto.writer) &&
                Objects.equals(price, bookDto.price) &&
                Objects.equals(ownerId, bookDto.ownerId) &&
                Objects.equals(publishDate, bookDto.publishDate) &&
                Objects.equals(isbn, bookDto.isbn) &&
                Objects.equals(maxBorrowDay, bookDto.maxBorrowDay) &&
                Objects.equals(categoryId, bookDto.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, writer, price, ownerId, publishDate, isbn, maxBorrowDay, categoryId);
    }
}
