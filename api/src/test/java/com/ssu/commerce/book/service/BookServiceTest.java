package com.ssu.commerce.book.service;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.mapper.BookMapper;
import com.ssu.commerce.book.dto.mapper.SelectBookListParamDtoMapper;
import com.ssu.commerce.book.dto.mapper.UpdateBookParamDtoMapper;
import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.model.Category;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.book.persistence.CategoryRepository;
import com.ssu.commerce.book.supplier.BookTestDataSupplier;
import com.ssu.commerce.core.exception.NotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class BookServiceTest implements BookTestDataSupplier {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getBookList() {

        Page<Book> returnBookPage = new PageImpl<>(BookTestDataSupplier.getBookList());
        GetBookListParamDto paramDto = BookTestDataSupplier.getGetBookListParamDto();

        when(bookRepository.selectBookPage(SelectBookListParamDtoMapper.INSTANCE.map(paramDto),
                paramDto.getPageable())).thenReturn(returnBookPage);

        Page<BookDto> result = bookService.getBookList(paramDto);
        Page<BookDto> expectedBookList = BookTestDataSupplier.getBookDtoList();

        assertEquals(result, expectedBookList);

        verify(bookRepository, times(1)).
                selectBookPage(
                        SelectBookListParamDtoMapper.INSTANCE.map(paramDto),
                        paramDto.getPageable()
                );
    }

    @Test
    void getBookDetail() {

        Optional<Book> book = Optional.ofNullable(BookTestDataSupplier.getBook());
        UUID id = UUID.randomUUID();
        when(bookRepository.findById(id)).thenReturn(book);

        BookDetailDto bookDetailDto = bookService.getBookDetail(id);
        BookDetailDto expectedBookDetailDto = BookTestDataSupplier.getBookDetailDto();

        assertEquals(bookDetailDto, expectedBookDetailDto);

        verify(bookRepository, times(1)).findById(id);

    }

    @Test
    void getBookDetailError() {

        UUID bookId = UUID.randomUUID();
        String expectedErrorMessage = "book not found; bookId=" + bookId;


        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.getBookDetail(bookId);
        });

        assertEquals(expectedErrorMessage, exception.getMessage());

    }

    @Test
    void registerBook() {
        UUID categoryId = TEST_VAL_BOOK_CATEGORY_ID;
        Optional<Category> category = Optional.ofNullable(BookTestDataSupplier.getCategory());
        RegisterBookParamDto registerBookParamDto = BookTestDataSupplier.getRegisterBookParamDto();
        Book book = BookTestDataSupplier.getBookWithId();


        when(categoryRepository.findById(categoryId)).thenReturn(category);
        when(bookRepository.save(BookMapper.INSTANCE.map(registerBookParamDto))).thenReturn(book);

        UUID registerBookId = bookService.registerBook(registerBookParamDto);

        assertEquals(registerBookId, TEST_VAL_BOOK_ID);

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(bookRepository, times(1)).save(BookMapper.INSTANCE.map(registerBookParamDto));
    }

    @Test
    void registerBookError() {
        UUID categoryId = TEST_VAL_BOOK_CATEGORY_ID;
        RegisterBookParamDto registerBookParamDto = BookTestDataSupplier.getRegisterBookParamDto();

        String expectedErrorMessage = "category not found; categoryId=" + categoryId;
        Book book = BookTestDataSupplier.getBookWithId();


        when(bookRepository.save(any())).thenReturn(book);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.registerBook(registerBookParamDto);
        });

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void changeBook() {

        Optional<Category> category = Optional.ofNullable(BookTestDataSupplier.getCategory());
        Book book = BookTestDataSupplier.getBookWithId();
        UUID bookId = TEST_VAL_BOOK_ID;
        UUID categoryId = TEST_VAL_BOOK_CATEGORY_ID;
        ChangeBookParamDto changeBookParamDto = BookTestDataSupplier.getChangeBookParamDto();
        Book changedBook = BookTestDataSupplier.getChangedBook();

        when(categoryRepository.findById(categoryId)).thenReturn(category);
        when(bookRepository.findById(bookId)).thenReturn(Optional.ofNullable(book));
        when(bookRepository.changeBook(UpdateBookParamDtoMapper.INSTANCE.map(changeBookParamDto))).thenReturn(book);

        UUID changeId = bookService.changeBook(changeBookParamDto);
        assertEquals(changeId, bookId);
        assertEquals(changedBook, book);

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void changeBookCategoryError() {
        UUID categoryId = TEST_VAL_BOOK_CATEGORY_ID;
        String expectedErrorMessage = "category not found; categoryId=" + categoryId;
        ChangeBookParamDto changeBookParamDto = BookTestDataSupplier.getChangeBookParamDto();

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.changeBook(changeBookParamDto);
        });

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void changeBook_BookError() {
        UUID bookId = TEST_VAL_BOOK_ID;
        String expectedErrorMessage = "book not found; bookId=" + bookId;
        ChangeBookParamDto changeBookParamDto = BookTestDataSupplier.getChangeBookParamDto();
        UUID categoryId = TEST_VAL_BOOK_CATEGORY_ID;
        Optional<Category> category = Optional.ofNullable(BookTestDataSupplier.getCategory());

        when(categoryRepository.findById(categoryId)).thenReturn(category);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.changeBook(changeBookParamDto);
        });

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void deleteBook() {
        Book book = BookTestDataSupplier.getBookWithId();
        UUID bookId = TEST_VAL_BOOK_ID;
        when(bookRepository.findById(bookId)).thenReturn(Optional.ofNullable(book));

        UUID deleteId = bookService.deleteBook(bookId);
        assertEquals(deleteId, bookId);
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void deleteBookError() {
        UUID bookId = TEST_VAL_BOOK_ID;
        String expectedErrorMessage = "book not found; bookId=" + bookId;

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.deleteBook(bookId);
        });

        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}
