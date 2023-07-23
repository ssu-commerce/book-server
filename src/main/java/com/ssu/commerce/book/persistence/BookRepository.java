package com.ssu.commerce.book.persistence;


import com.ssu.commerce.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
}
