package com.ssu.commerce.book.model;

import com.ssu.commerce.book.service.BookService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;


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
@Table(name = "book_info")
public class BookEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String title;
    private String imageUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_details_id")
    private BookDetail bookDetail;
}
