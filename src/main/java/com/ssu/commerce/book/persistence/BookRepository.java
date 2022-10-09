package com.ssu.commerce.book.persistence;


import com.ssu.commerce.book.dto.response.GetBookInfoResponseDto;
import com.ssu.commerce.book.model.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {

//    Page<Book> findByIdOrderByIdDesc(Book book, Pageable pageable);

    @NotNull
    Optional<GetBookInfoResponseDto> findProjectedById(Long bookId);
}
