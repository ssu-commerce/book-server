package com.ssu.commerce.book.model;

import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


/*
         *  1. 도서 조회 API call (성엽)
         *   도서 상세 내용을 조회한다.
         *   -> request (bookId)
         *   -> response (bookId, 대출가능여부, 대여가능기간, 대여 장소(책 주인 위치), 대여비, 보증금, 배송 flag - 픽업 or 배송, 배송비 고정)
        * 아이디, 책이름 , 상세보기 -> (페이지정보 출판사 필요시, 돈정보) , 책상태, 이미지주소, 거래가능지역
 */
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
    @Column(name = "book_id", columnDefinition = "CHAR(36)")
    private UUID bookId;

    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(200) CHARACTER SET UTF8")
    private String content;

    @Column(name = "writer", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    private String writer;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "share_price", nullable = false)
    private Long sharePrice;

    @Column(name = "comment", columnDefinition = "TEXT CHARACTER SET UTF8")
    private String comment;

    @Column(name = "start_borrow_day", nullable = false)
    private LocalDateTime startBorrowDay;

    @Column(name = "end_borrow_day", nullable = false)
    private LocalDateTime endBorrowDay;

    @Column(name = "owner_id", columnDefinition = "CHAR(36)")
    private UUID ownerId;

    @Column(name = "publish_date", nullable = false)
    private LocalDateTime publishDate;

    @Column(name = "isbn", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    private String isbn;

    @Column(name = "category_id", columnDefinition = "CHAR(36)", nullable = false)
    private UUID categoryId;

    @Column(name = "book_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookState bookState;

    public Book update(ChangeBookParamDto paramDto) {
        title = paramDto.getTitle();
        content = paramDto.getContent();
        writer = paramDto.getWriter();
        price = paramDto.getPrice();
        sharePrice = paramDto.getSharePrice();
        comment = paramDto.getComment();
        startBorrowDay = paramDto.getStartBorrowDay();
        endBorrowDay = paramDto.getEndBorrowDay();
        ownerId = paramDto.getOwnerId();
        publishDate = paramDto.getPublishDate();
        isbn = paramDto.getIsbn();
        categoryId = paramDto.getCategoryId();
        return this;
    }
    public void updateBookState(BookState bookState) {
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
