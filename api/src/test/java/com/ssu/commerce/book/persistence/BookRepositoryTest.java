package com.ssu.commerce.book.persistence;

import com.ssu.commerce.book.config.QuerydslConfig;
import com.ssu.commerce.book.dto.param.query.SelectBookListParamDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.model.Category;
import com.ssu.commerce.book.supplier.BookTestDataSupplier;
import com.ssu.commerce.core.jpa.JpaConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;

import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
@DirtiesContext
@ActiveProfiles("test")
@Import({JpaConfig.class, QuerydslConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest implements BookTestDataSupplier {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        final Category category = categoryRepository.save(BookTestDataSupplier.getCategory());

        final Book book = BookTestDataSupplier.getBook();
        book.setCategoryId(category.getId());

        bookRepository.save(book);
    }

    @Test
    void testSelectBookPage() {
        final Page<Book> bookPage = bookRepository.selectBookPage(
                SelectBookListParamDto.builder()
                        .title("비가 오면")
                        .build(),
                Pageable.unpaged()
        );

        assertAll(
                "메소드 호출 결과를 검증합니다.",
                () -> assertNotNull(bookPage.getContent()),
                () -> assertFalse(CollectionUtils.isEmpty(bookPage.getContent())),
                () -> assertNotNull(bookPage.getContent().get(0).getCategoryId()),
                () -> assertEquals(TEST_VAL_BOOK_TITLE, bookPage.getContent().get(0).getTitle()),
                () -> assertEquals(TEST_VAL_BOOK_CONTENT, bookPage.getContent().get(0).getContent()),
                () -> assertEquals(TEST_VAL_BOOK_WRITER, bookPage.getContent().get(0).getWriter()),
                () -> assertEquals(TEST_VAL_BOOK_PRICE, bookPage.getContent().get(0).getPrice()),
                () -> assertEquals(TEST_VAL_BOOK_PUBLISH_DATE, bookPage.getContent().get(0).getPublishDate()),
                () -> assertEquals(TEST_VAL_BOOK_ISBN, bookPage.getContent().get(0).getIsbn()),
                () -> assertEquals(TEST_VAL_BOOK_MAX_BORROW_DAY, bookPage.getContent().get(0).getMaxBorrowDay())
        );
    }
}