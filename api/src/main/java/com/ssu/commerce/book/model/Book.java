package com.ssu.commerce.book.model;

import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(name = "price")
    private Long price;

    @Column(name = "share_price")
    private Long sharePrice;

    @Column(name = "comment")
    private String comment;

    @Column(name = "start_borrow_day")
    private LocalDateTime startBorrowDay;

    @Column(name = "end_borrow_day")
    private LocalDateTime endBorrowDay;

    @Column(name = "owner_id", columnDefinition = "BINARY(16)")
    private UUID ownerId;

    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "category_id", columnDefinition = "BINARY(16)")
    private UUID categoryId;

    @Column(name = "book_state")
    @Enumerated(EnumType.STRING)
    private BookState bookState;

    public Book update(@NotNull final ChangeBookParamDto paramDto) {
        title = paramDto.getTitle();
        content = paramDto.getContent();
        writer = paramDto.getWriter();
        price = paramDto.getPrice();
        sharePrice = paramDto.getSharePrice();
        publishDate = paramDto.getPublishDate();
        isbn = paramDto.getIsbn();
        startBorrowDay = paramDto.getStartBorrowDay();
        endBorrowDay = paramDto.getEndBorrowDay();
        categoryId = paramDto.getCategoryId();
        return this;
    }
    public void updateBookState(@NotNull final BookState bookState) {
        this.bookState = bookState;
    }

    public boolean rental() {
        if(isPossibleRentalState()) {
            updateBookState(BookState.DISSHAREABLE);
            return true;
        }

        return false;
    }

    boolean isPossibleRentalState() {
        return bookState == BookState.SHARABLE;
    }
}
