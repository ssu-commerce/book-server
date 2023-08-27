package com.ssu.commerce.book.supplier;

import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.model.Category;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface BookTestDataSupplier {
    Long TEST_VAL_BOOK_ID = 10L;
    String TEST_VAL_BOOK_TITLE = "비가 오면 열리는 상점";
    String TEST_VAL_BOOK_CONTENT = "불행을 파는 대신 원하는 행복을 살 수 있는 가게가 있다면? " +
            "듣기만 해도 방문하고 싶어지는, 비가 오면 열리는 수상한 상점에 초대된 여고생 세린이 안내묘 잇샤, " +
            "사람의 마음을 훔치는 도깨비들과 함께 펼치는 감동 모험 판타지.";
    String TEST_VAL_BOOK_WRITER = "유영광";
    Long TEST_VAL_BOOK_PRICE = 15120L;
    UUID TEST_VAL_BOOK_OWNER_ID = UUID.fromString("b794d677-aa71-432f-8016-dbbf4cfe09c7");
    LocalDateTime TEST_VAL_BOOK_PUBLISH_DATE = LocalDateTime.of(2023,7,23,0,0,0);
    String TEST_VAL_BOOK_ISBN = "9791198173898";
    Long TEST_VAL_BOOK_MAX_BORROW_DAY = 15L;
    //Long TEST_VAL_BOOK_CATEGORY_ID = "test";

    UUID TEST_VAL_BOOK_CATEGORY_ID = UUID.fromString("578caff7-1c44-4ede-92d2-b6e6cf60c0aa");

    String TEST_VAL_CATEGORY_NAME = "한국소설일반";
    String TEST_VAL_CATEGORY_DESCRIPTION = "소설";


    String TEST_VAL_CHANGE_BOOK_WRITER = "비가";
    Long TEST_VAL_CHANGE_BOOK_PRICE = 200000L;
    Long TEST_VAL_CHANGE_BOOK_OWNER_ID = 1L;
    LocalDateTime TEST_VAL_CHANGE_BOOK_PUBLISH_DATE = LocalDateTime.now();
    String TEST_VAL_CHANGE_BOOK_ISBN = "9791198173898";
    Long TEST_VAL_CHANGE_BOOK_MAX_BORROW_DAY = 15L;
    UUID TEST_VAL_CHANGE_BOOK_CATEGORY_ID = UUID.fromString("f29eb3c9-2e17-4e62-80d9-07573dec62f8");
    String TEST_VAL_CHANGE_BOOK_TITLE = "화산귀환";
    String TEST_VAL_CHANGE_BOOK_CONTENT = "대 화산파 13대 제자. 천하삼대검수. 매화검존 청명 " +
            "천하를 혼란에 빠뜨린 고금제일마 천마의 목을 치고 십만대산의 정상에서 영면. 백 년의 시간을 뛰어넘어 아이의 몸으로 다시 살아나다. " +
            "그런데...... 뭐? 화산이 망해? 이게 뭔 개소리야!? " +
            "망했으면 살려야 하는 게 인지상정.";

    static Book getBook() {
        return Book.builder()
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
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

    static GetBookListParamDto getGetBookListParamDto() {
        return GetBookListParamDto.builder()
                .title(TEST_VAL_BOOK_TITLE)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .pageable(Pageable.unpaged())
                .build();
    }

    static ChangeBookParamDto updateBook(UUID id, UUID categoryId) {
        return ChangeBookParamDto.builder()
                .id(id)
                .title(TEST_VAL_CHANGE_BOOK_TITLE)
                .content(TEST_VAL_CHANGE_BOOK_CONTENT)
                .writer(TEST_VAL_CHANGE_BOOK_WRITER)
                .price(TEST_VAL_CHANGE_BOOK_PRICE)
                .publishDate(TEST_VAL_CHANGE_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_CHANGE_BOOK_ISBN)
                .maxBorrowDay(TEST_VAL_CHANGE_BOOK_MAX_BORROW_DAY)
                .categoryId(categoryId)
                .build();
    }
}
