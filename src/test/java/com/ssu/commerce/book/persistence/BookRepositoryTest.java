package com.ssu.commerce.book.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.core.configs.EnableSsuCommerceCore;
import com.ssu.commerce.core.jpa.JpaConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Import({JpaConfig.class})
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        Book entity = new Book();
        entity.setTitle("title1");
        entity.setPrice(1000L);
        entity.setImageUrl("url");
        bookRepository.save(entity);
    }

    @Test
    public void test() {
        bookRepository.findProjectedById(1L);
    }
}