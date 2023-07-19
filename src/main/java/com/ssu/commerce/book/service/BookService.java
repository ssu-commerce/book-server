package com.ssu.commerce.book.service;


import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.mapper.BookDtoMapper;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.core.exception.NotFoundException;
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

    public Page<BookDto> getBookList(
            @NonNull final GetBookListParamDto paramDto
    ) {
        final Page<Book> bookList =
                bookRepository.findAllByTitleLikeIgnoreCaseOrCategoryId(
                        paramDto.getTitle(),
                        paramDto.getCategoryId(),
                        paramDto.getPageable()
                );

        return new PageImpl<>(
                BookDtoMapper.INSTANCE.mapToList(bookList.getContent()),
                bookList.getPageable(),
                bookList.getTotalElements()
        );
    }

    public Book getBookInfo(
            Long id
    ) {

        Book book = bookRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("book not found; bookId=%s", id),
                                "BOOK_001"
                        ));

        return book;
    }

}
