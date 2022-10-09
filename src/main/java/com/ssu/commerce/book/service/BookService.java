package com.ssu.commerce.book.service;


import com.ssu.commerce.book.dto.request.GetBookInfoListRequestDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.core.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

//    public List<SearchBookDTO> findAll(Pageable pageable){
//        return bookRepository.findByIdOrderByIdDesc(new Book(), pageable)
//                .map(SearchBookDTO::new).getContent();
//
//    }

    public Page<Book> getBookInfoList(
            GetBookInfoListRequestDto requestDto,
            Pageable pageable
    ) {

        Page<Book> bookList = bookRepository.findAll(
                pageable
        );

        return bookList;
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
