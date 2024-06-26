package com.ssu.commerce.book.controller;


import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.mapper.*;
import com.ssu.commerce.book.dto.param.DeleteBookParamDto;
import com.ssu.commerce.book.dto.request.ChangeBookRequestDto;
import com.ssu.commerce.book.dto.request.RegisterBookRequestDto;
import com.ssu.commerce.book.dto.response.*;
import com.ssu.commerce.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/book")
public class BookController {
    private final BookService bookService;

    // 리스트 검색
    @GetMapping("")
    public Page<GetBookResponseDto> getBookList(
            @Nullable @RequestParam String title,
            @Nullable @RequestParam UUID categoryId,
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
            @PathVariable final UUID id
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
                .bookId(
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
                .bookId(
                        bookService.changeBook(
                                ChangeBookParamDtoMapper.INSTANCE.map(requestDto)
                        ).getBookId()
                )
                .build();
    }

    @DeleteMapping("/{id}")
    public DeleteBookResponseDto deleteBook(
            @Valid @PathVariable final UUID id
    ) {
        log.debug("[deleteBook]deleteDto={}", id);

        return DeleteBookResponseDto.builder()
                .bookId(
                        bookService.deleteBook(
                                DeleteBookParamDto.builder()
                                        .bookId(id)
                                        .build()
                        )
                )
                .build();
    }
}
