package com.ssu.commerce.book.service;


import com.ssu.commerce.book.dto.SearchBookDTO;
import com.ssu.commerce.book.model.BookEntity;
import com.ssu.commerce.book.persistence.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<SearchBookDTO> findAll(Pageable pageable){
        return bookRepository.findByIdOrderByIdDesc(new BookEntity(), pageable)
                .map(SearchBookDTO::new).getContent();

    }

}
