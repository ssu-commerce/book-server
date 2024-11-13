package com.ssu.commerce.book.service;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.mapper.BookMapper;
import com.ssu.commerce.book.dto.mapper.SelectBookListParamDtoMapper;
import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.param.DeleteBookParamDto;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.model.Category;
import com.ssu.commerce.book.persistence.BookRepository;
import com.ssu.commerce.book.persistence.CategoryRepository;
import com.ssu.commerce.book.supplier.BookTestDataSupplier;
import com.ssu.commerce.core.error.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Optional;
import java.util.UUID;

import static com.ssu.commerce.book.supplier.CategoryTestDataSupplier.TEST_VAL_BOOK_CATEGORY_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        RegisterBookParamDto registerBookParamDto = BookTestDataSupplier.getRegisterBookParamDto();

        String expectedErrorMessage = "category not found; categoryId=" + TEST_VAL_BOOK_CATEGORY_ID;
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

        UUID changeId = bookService.changeBook(changeBookParamDto).getBookId();
        assertEquals(changeId, bookId);
        assertEquals(changedBook, book);

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void changeBookCategoryError() {
        String expectedErrorMessage = "category not found; categoryId=" + TEST_VAL_BOOK_CATEGORY_ID;
        ChangeBookParamDto changeBookParamDto = BookTestDataSupplier.getChangeBookParamDto();

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.changeBook(changeBookParamDto);
        });

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void changeBook_BookError() {
        String expectedErrorMessage = "book not found; bookId=" + TEST_VAL_BOOK_ID;
        ChangeBookParamDto changeBookParamDto = BookTestDataSupplier.getChangeBookParamDto();
        Optional<Category> category = Optional.ofNullable(BookTestDataSupplier.getCategory());

        when(categoryRepository.findById(TEST_VAL_BOOK_CATEGORY_ID)).thenReturn(category);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.changeBook(changeBookParamDto);
        });

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void deleteBook() {
        Book book = BookTestDataSupplier.getBookWithId();
        UUID bookId = TEST_VAL_BOOK_ID;
        DeleteBookParamDto deleteBookParamDto = BookTestDataSupplier.getDeleteBookParamDto(bookId);
        when(bookRepository.findById(bookId)).thenReturn(Optional.ofNullable(book));

        UUID deleteId = bookService.deleteBook(deleteBookParamDto);
        assertEquals(deleteId, bookId);
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void deleteBookError() {
        UUID bookId = TEST_VAL_BOOK_ID;
        String expectedErrorMessage = "book not found; bookId=" + bookId;
        DeleteBookParamDto deleteBookParamDto = BookTestDataSupplier.getDeleteBookParamDto(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.deleteBook(deleteBookParamDto);
        });

        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}
