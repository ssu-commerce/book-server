package com.ssu.commerce.book.service;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.model.Category;
import com.ssu.commerce.book.persistence.CategoryRepository;
import com.ssu.commerce.book.supplier.BookTestDataSupplier;
import com.ssu.commerce.core.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class BookServiceTest implements BookTestDataSupplier {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookService bookService;

    private Category category;
    private UUID bookId;

    @BeforeEach
    void setUp() {
        category = categoryRepository.save(BookTestDataSupplier.getCategory());
        bookId = bookService.registerBook(
                RegisterBookParamDto.builder()
                        .title(TEST_VAL_BOOK_TITLE)
                        .content(TEST_VAL_BOOK_CONTENT)
                        .writer(TEST_VAL_BOOK_WRITER)
                        .price(TEST_VAL_BOOK_PRICE)
                        .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                        .isbn(TEST_VAL_BOOK_ISBN)
                        .maxBorrowDay(TEST_VAL_BOOK_MAX_BORROW_DAY)
                        .categoryId(category.getId())
                        .build()
        );
    }

    @Test
    @Order(1)
    public void changeBook_shouldChangeBook() {

        ChangeBookParamDto dto = BookTestDataSupplier.updateBook(bookId, category.getId());
        UUID id = bookService.changeBook(dto);
        assertNotNull(id);

        BookDetailDto findBookDto = bookService.getBookDetail(id);
        assertEquals(findBookDto.getTitle(), TEST_VAL_CHANGE_BOOK_TITLE);
        assertEquals(findBookDto.getContent(), TEST_VAL_CHANGE_BOOK_CONTENT);
        assertEquals(findBookDto.getWriter(), TEST_VAL_CHANGE_BOOK_WRITER);
        assertEquals(findBookDto.getPrice(), TEST_VAL_CHANGE_BOOK_PRICE);
        assertEquals(findBookDto.getIsbn(), TEST_VAL_CHANGE_BOOK_ISBN);
        assertEquals(findBookDto.getMaxBorrowDay(), TEST_VAL_CHANGE_BOOK_MAX_BORROW_DAY);

    }

    @Test
    @Order(2)
    public void deleteBook_shouldDeleteBook() {
        BookDetailDto dto = bookService.getBookDetail(bookId);
        assertNotNull(dto);

        bookService.deleteBook(bookId);

        assertThrows(NotFoundException.class, () -> {
            bookService.getBookDetail(bookId);
        });
    }

    @Test
    @Order(3)
    public void deleteBook_shouldFail_whenIdIsInvalid() {
        assertThrows(NotFoundException.class, () -> {
            bookService.deleteBook(UUID.randomUUID());
        });
    }

    @Test
    @Order(4)
    public void changeBook_shouldFail_whenIdIsInvalid() {
        ChangeBookParamDto paramDto = ChangeBookParamDto.builder()
                .id(UUID.randomUUID())
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .maxBorrowDay(TEST_VAL_BOOK_MAX_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .build();

        assertThrows(NotFoundException.class, () -> {
            bookService.changeBook(paramDto);
        });
    }
}
