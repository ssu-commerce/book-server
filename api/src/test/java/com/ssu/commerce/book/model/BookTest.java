package com.ssu.commerce.book.model;

import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.supplier.BookTestDataSupplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookTest implements BookTestDataSupplier {
    @Test
    void testUpdateBookState() {
        final Book book = BookTestDataSupplier.getBook();
        book.updateBookState(BookState.SHARING);
        assertEquals(
                BookState.SHARING,
                book.getBookState()
        );
    }
    @Test
    void testRental() {
        assertTrue(BookTestDataSupplier.getBook().isPossibleRentalState());
    }

    @Test
    void testIsPossibleRentalState() {
        final Book book = BookTestDataSupplier.getBook();

        assertTrue(book.rental());
        assertEquals(
                BookState.DISSHAREABLE,
                book.getBookState()
        );
    }

    @Test
    void testRentalImpossible() {
        final Book book = BookTestDataSupplier.getBook();
        book.updateBookState(BookState.SHARING);

        assertFalse(book.rental());
        assertEquals(
                BookState.SHARING,
                book.getBookState()
        );
    }
}
