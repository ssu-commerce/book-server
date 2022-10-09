package com.ssu.commerce.book.controller;


import com.ssu.commerce.book.dto.request.GetBookInfoListRequestDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


/*
 *  1. 도서 조회 API call (성엽)
 *   도서 상세 내용을 조회한다.
 *   -> request (bookId)
 *   -> response (bookId, 대출가능여부, 대여가능기간, 대여 장소(책 주인 위치), 대여비, 보증금, 배송 flag - 픽업 or 배송, 배송비 고정)

 */
@Slf4j
@RestController
@RequestMapping("/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/health")
    public String health(){
        return "200 OK";
    }
//    @GetMapping("post")
//    public List<SearchBookDTO> find(Pageable pageable){
//        return bookService.findAll(pageable);
//    }


    // 리스트 검색
    @GetMapping("")
    public Page<Book> getBookInfoList(
            GetBookInfoListRequestDto requestDto,
            Pageable pageable
    ) {

        log.debug("[getBookInfoList] requestDto={}", requestDto);

        return bookService.getBookInfoList(requestDto, pageable);
    }


    // 단건 상세 조회
    @GetMapping("/{id}")
    public Book getBookInfo(
            @PathVariable Long id
    ) {

        return bookService.getBookInfo(id);
    }

}
