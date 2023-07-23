package com.ssu.commerce.book.supplier;

import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.model.Category;

import java.time.LocalDateTime;

public interface BookTestDataSupplier {
    Long TEST_VAL_BOOK_ID = 10L;
    String TEST_VAL_BOOK_TITLE = "비가 오면 열리는 상점";
    String TEST_VAL_BOOK_CONTENT = "불행을 파는 대신 원하는 행복을 살 수 있는 가게가 있다면? " +
            "듣기만 해도 방문하고 싶어지는, 비가 오면 열리는 수상한 상점에 초대된 여고생 세린이 안내묘 잇샤, " +
            "사람의 마음을 훔치는 도깨비들과 함께 펼치는 감동 모험 판타지.";
    String TEST_VAL_BOOK_WRITER = "유영광";
    Long TEST_VAL_BOOK_PRICE = 15120L;
    Long TEST_VAL_BOOK_OWNER_ID = 1L;
    LocalDateTime TEST_VAL_BOOK_PUBLISH_DATE = LocalDateTime.of(2023,7,23,0,0,0);
    String TEST_VAL_BOOK_ISBN = "9791198173898";
    Long TEST_VAL_BOOK_MAX_BORROW_DAY = 15L;
    Long TEST_VAL_BOOK_CATEGORY_ID = 1L;

    String TEST_VAL_CATEGORY_NAME = "한국소설일반";
    String TEST_VAL_CATEGORY_DESCRIPTION = "소설";

    static Book getBook() {
        return Book.builder()
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .ownerId(TEST_VAL_BOOK_OWNER_ID)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .maxBorrowDay(TEST_VAL_BOOK_MAX_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .build();
    }

    static Category getCategory() {
        return Category.builder()
                .name(TEST_VAL_CATEGORY_NAME)
                .description(TEST_VAL_CATEGORY_DESCRIPTION)
                .build();
    }
}
