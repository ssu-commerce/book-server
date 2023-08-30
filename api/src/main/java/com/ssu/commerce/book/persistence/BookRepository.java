package com.ssu.commerce.book.persistence;


import com.ssu.commerce.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BookRepository extends JpaRepository<Book, UUID>, BookRepositoryCustom {
}
