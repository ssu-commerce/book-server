package com.ssu.commerce.book.model;

import com.ssu.commerce.core.jpa.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


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
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(name = "price")
    private Long price;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "max_borrow_day")
    private Long maxBorrowDay;

    private Long categoryId;
}
