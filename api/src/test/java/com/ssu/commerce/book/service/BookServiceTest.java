package com.ssu.commerce.book.service;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.persistence.CategoryRepository;
import com.ssu.commerce.book.supplier.BookTestDataSupplier;
import com.ssu.commerce.core.exception.NotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookServiceTest implements BookTestDataSupplier {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookService bookService;

    @BeforeAll
    void setUp() {
        categoryRepository.save(BookTestDataSupplier.getCategory());
        bookService.registerBook(
                RegisterBookParamDto.builder()
                        .title(TEST_VAL_BOOK_TITLE)
                        .content(TEST_VAL_BOOK_CONTENT)
                        .writer(TEST_VAL_BOOK_WRITER)
                        .price(TEST_VAL_BOOK_PRICE)
                        .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                        .isbn(TEST_VAL_BOOK_ISBN)
                        .maxBorrowDay(TEST_VAL_BOOK_MAX_BORROW_DAY)
                        .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                        .build()
        );
    }

    @Test
    @Order(1)
    public void changeBook_shouldChangeBook() {
        ChangeBookParamDto dto = BookTestDataSupplier.updateBook();
        Long id = bookService.changeBook(dto);
        assertEquals(1L, id);

        BookDetailDto findBookDto = bookService.getBookDetail(1L);
        assertEquals(findBookDto.getId(), 1L);
        assertEquals(findBookDto.getTitle(), TEST_VAL_CHANGE_BOOK_TITLE);
        assertEquals(findBookDto.getContent(), TEST_VAL_CHANGE_BOOK_CONTENT);
        assertEquals(findBookDto.getWriter(), TEST_VAL_CHANGE_BOOK_WRITER);
        assertEquals(findBookDto.getPrice(), TEST_VAL_CHANGE_BOOK_PRICE);
        assertEquals(findBookDto.getIsbn(), TEST_VAL_CHANGE_BOOK_ISBN);
        assertEquals(findBookDto.getMaxBorrowDay(), TEST_VAL_CHANGE_BOOK_MAX_BORROW_DAY);
        assertEquals(findBookDto.getCategoryId(), TEST_VAL_CHANGE_BOOK_CATEGORY_ID);

        Page<BookDto> bookDtoPage = bookService.getBookList(
                GetBookListParamDto.builder()
                        .pageable(PageRequest.of(0,10))
                        .build()
        );
        assertEquals(bookDtoPage.getContent().size(), 1);
    }

    @Test
    @Order(2)
    public void deleteBook_shouldDeleteBook() {
        Long id = bookService.deleteBook(1L);

        assertEquals(1L, id);

        Page<BookDto> bookDtoPage = bookService.getBookList(
                GetBookListParamDto.builder()
                        .pageable(PageRequest.of(0,10))
                        .build()
        );

        assertEquals(bookDtoPage.getContent().size(), 0);
    }

    @Test
    @Order(3)
    public void deleteBook_shouldFail_whenIdIsInvalid() {
        assertThrows(NotFoundException.class, () -> {
            bookService.deleteBook(-1L);
        });
    }

    @Test
    @Order(4)
    public void changeBook_shouldFail_whenIdIsInvalid() {
        ChangeBookParamDto paramDto = ChangeBookParamDto.builder()
                .id(-1L)
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
