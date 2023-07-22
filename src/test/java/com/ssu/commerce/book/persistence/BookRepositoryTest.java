package com.ssu.commerce.book.persistence;

import com.ssu.commerce.book.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;


//@DataJpaTest
//@Import({JpaConfig.class})
@SpringBootTest
@Transactional
//@Rollback
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        final Book book = Book.builder()
                .title("비가 오면 열리는 상점")
                .content("불행을 파는 대신 원하는 행복을 살 수 있는 가게가 있다면? " +
                                 "듣기만 해도 방문하고 싶어지는, 비가 오면 열리는 수상한 상점에 초대된 여고생 세린이 안내묘 잇샤, " +
                                 "사람의 마음을 훔치는 도깨비들과 함께 펼치는 감동 모험 판타지.")
                .writer("유영광")
                .price(15120L)
                .ownerId(1L)
                .publishDate(LocalDateTime.now())
                .isbn("9791198173898")
                .maxBorrowDay(15L)
                .categoryId(1L)
                .build();

        bookRepository.save(book);
    }

    @Test
    void testFindAllByTitleLikeIgnoreCaseOrCategoryId() {
        final Page<Book> bookPage = bookRepository.findAllByTitleLikeIgnoreCaseOrCategoryId(
                "비가",
                null,
                Pageable.unpaged()
        );

        assertNotNull(bookPage.getContent());
    }
}