package com.ssu.commerce.book.persistence;


import com.ssu.commerce.book.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,String> {

    List<BookEntity> findByBookId(String bookId);
}
