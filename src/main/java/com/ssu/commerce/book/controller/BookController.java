package com.ssu.commerce.book.controller;


import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.mapper.*;
import com.ssu.commerce.book.dto.request.ChangeBookRequestDto;
import com.ssu.commerce.book.dto.request.DeleteBookRequestDto;
import com.ssu.commerce.book.dto.request.RegisterBookRequestDto;
import com.ssu.commerce.book.dto.response.*;
import com.ssu.commerce.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/*
 *  1. 도서 조회 API call (성엽)
 *   도서 상세 내용을 조회한다.
 *   -> request (bookId)
 *   -> response (bookId, 대출가능여부, 대여가능기간, 대여 장소(책 주인 위치), 대여비, 보증금, 배송 flag - 픽업 or 배송, 배송비 고정)

 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/book")
public class BookController {
    private final BookService bookService;

    // 리스트 검색
    @GetMapping("")
    public Page<GetBookResponseDto> getBookList(
            @RequestParam String title,
            @RequestParam Long categoryId,
            Pageable pageable
    ) {

        log.debug("[getBookList]title={},categoryId={}", title, categoryId);

        final Page<BookDto> bookList = bookService.getBookList(
                GetBookListParamMapper.INSTANCE.map(title, categoryId, pageable)
        );

        return new PageImpl<>(
                GetBookResponseDtoMapper.INSTANCE.mapToList(bookList.getContent()),
                bookList.getPageable(),
                bookList.getTotalElements()
        );
    }


    // 단건 상세 조회
    @GetMapping("/{id}")
    public GetBookDetailResponseDto getBookDetail(
            @PathVariable final Long id
    ) {

        log.debug("[getBook]id={}", id);

        return GetBookDetailResponseDtoMapper.INSTANCE.map(
                bookService.getBookDetail(id)
        );
    }

    @PostMapping("")
    public RegisterBookResponseDto registerBook(
            @Valid @RequestBody final RegisterBookRequestDto requestDto
    ) {

        log.debug("[registerBook]requestDto={}", requestDto);

        return RegisterBookResponseDto.builder()
                .id(
                        bookService.registerBook(
                                RegisterBookParamDtoMapper.INSTANCE.map(requestDto)
                        )
                )
                .build();
    }

    @PutMapping("")
    public ChangeBookResponseDto changeBook(
            @Valid @RequestBody final ChangeBookRequestDto requestDto
    ) {

        log.debug("[changeBook]requestDto={}", requestDto);

        return ChangeBookResponseDto.builder()
                .id(
                        bookService.changeBook(
                                ChangeBookParamDtoMapper.INSTANCE.map(requestDto)
                        )
                )
                .build();
    }

    @DeleteMapping("")
    public DeleteBookResponseDto deleteBook(
            @Valid @RequestBody final DeleteBookRequestDto requestDto
    ) {

        log.debug("[deleteBook]deleteDto={}", requestDto);

        return DeleteBookResponseDto.builder()
                .id(
                        bookService.deleteBook(requestDto.getId())
                )
                .build();
    }

}
