package com.ssu.commerce.book.service;


import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.mapper.*;
import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.book.persistence.CategoryRepository;
import com.ssu.commerce.core.exception.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

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
            @NonNull final UUID id
    ) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("book not found; bookId=%s", id),
                        "BOOK_001"
                ));

        return BookDetailDtoMapper.INSTANCE.map(book);
    }

    @Transactional
    public UUID registerBook(
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

    @Transactional
    public UUID changeBook(
            @NonNull @Valid final ChangeBookParamDto paramDto
    ) {
        categoryRepository.findById(paramDto.getCategoryId())
                        .orElseThrow(() -> new NotFoundException(
                        String.format("category not found; categoryId=%s", paramDto.getCategoryId()),
                        "BOOK_002"
        ));

        return bookRepository.changeBook(
                UpdateBookParamDtoMapper.INSTANCE.map(
                        paramDto)
                ).getId();
    }

    @Transactional
    public UUID deleteBook(
            @NonNull @Valid final UUID id
    ) {
        Book findBook = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("book not found; bookId=%s", id),
                        "BOOK_001"
                ));
        bookRepository.delete(findBook);
        return id;
    }
}
