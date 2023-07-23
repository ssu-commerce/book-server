package com.ssu.commerce.book.service;


import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.mapper.BookDetailDtoMapper;
import com.ssu.commerce.book.dto.mapper.BookDtoMapper;
import com.ssu.commerce.book.dto.mapper.BookMapper;
import com.ssu.commerce.book.dto.mapper.SelectBookListParamDtoMapper;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.book.persistence.CategoryRepository;
import com.ssu.commerce.core.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public Page<BookDto> getBookList(
            @NonNull final GetBookListParamDto paramDto
    ) {
        final Page<Book> bookList =
                bookRepository.selectBookPage(
                        SelectBookListParamDtoMapper.INSTANCE.map(paramDto),
                        paramDto.getPageable()
                );

        return new PageImpl<>(
                BookDtoMapper.INSTANCE.mapToList(bookList.getContent()),
                bookList.getPageable(),
                bookList.getTotalElements()
        );
    }

    public BookDetailDto getBookDetail(
            @NonNull final Long id
    ) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("book not found; bookId=%s", id),
                        "BOOK_001"
                ));

        return BookDetailDtoMapper.INSTANCE.map(book);
    }

    public Long registerBook(
            @NonNull @Valid final RegisterBookParamDto paramDto
    ) {
        categoryRepository.findById(paramDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("category not found; categoryId=%s", paramDto.getCategoryId()),
                        "BOOK_002"
                ));

        return bookRepository.save(
                BookMapper.INSTANCE.map(paramDto)
        ).getId();
    }

}
