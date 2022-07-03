package com.ssu.commerce.book.persistence;


import com.ssu.commerce.book.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<BookEntity,String> {

    Page<BookEntity> findByIdOrderByIdDesc(BookEntity bookEntity, Pageable pageable);
}
