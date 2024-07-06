package com.ssu.commerce.book.supplier;

import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.param.DeleteBookParamDto;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.model.Category;
import com.ssu.commerce.grpc.RentalBookRequest;
import com.ssu.commerce.grpc.UpdateBookStateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface BookTestDataSupplier {
    UUID TEST_VAL_BOOK_ID = UUID.fromString("60180817-de61-4bac-a777-16219ffb92c0");
    UUID TEST_VAL_ANOTHER_BOOK_ID = UUID.fromString("4de76436-a242-4664-9334-7bef0b6428bf");
    String TEST_VAL_BOOK_TITLE = "비가 오면 열리는 상점";
    String TEST_VAL_BOOK_CONTENT = "불행을 파는 대신 원하는 행복을 살 수 있는 가게가 있다면? " +
            "듣기만 해도 방문하고 싶어지는, 비가 오면 열리는 수상한 상점에 초대된 여고생 세린이 안내묘 잇샤, " +
            "사람의 마음을 훔치는 도깨비들과 함께 펼치는 감동 모험 판타지.";
    String TEST_VAL_BOOK_COMMENT = "이 책은 13페이지가 살짝 찢어져 있습니다.";
    String TEST_VAL_BOOK_WRITER = "유영광";
    Long TEST_VAL_BOOK_PRICE = 15120L;
    Long TEST_VAL_SHARED_BOOK_PRICE = 15120L;
    UUID TEST_VAL_BOOK_OWNER_ID = UUID.fromString("b794d677-aa71-432f-8016-dbbf4cfe09c7");
    LocalDateTime TEST_VAL_BOOK_PUBLISH_DATE = LocalDateTime.of(2023,7,23,0,0,0);
    String TEST_VAL_BOOK_ISBN = "9791198173898";
    LocalDateTime TEST_VAL_BOOK_START_BORROW_DAY = LocalDateTime.of(2024, 5, 13, 15, 30);
    LocalDateTime TEST_VAL_BOOK_END_BORROW_DAY =  LocalDateTime.of(2025, 5, 13, 15, 30);

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

    BookState TEST_VAL_BOOK_STATE = BookState.SHARABLE;
    BookState TEST_VAL_BOOK_STATE_SHARERABLE = BookState.SHARABLE;
    BookState TEST_VAL_BOOK_STATE_DISSHARERABLE = BookState.SHARABLE;

    String TEST_VAL_ACCESS_TOKE = "test access token";

    static Book getBook() {
        return Book.builder()
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .bookState(TEST_VAL_BOOK_STATE)
                .comment(TEST_VAL_BOOK_COMMENT)
                .build();
    }

    static Book getBookWithCategoryId(UUID categoryId) {
        return Book.builder()
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                .categoryId(categoryId)
                .bookState(TEST_VAL_BOOK_STATE)
                .comment(TEST_VAL_BOOK_COMMENT)
                .build();
    }

    static Book getBookWithState() {
        return Book.builder()
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .bookState(TEST_VAL_BOOK_STATE)
                .comment(TEST_VAL_BOOK_COMMENT)
                .bookState(TEST_VAL_BOOK_STATE)
                .build();
    }

    static Book getBookWithSharableState() {
        return Book.builder()
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .bookState(TEST_VAL_BOOK_STATE)
                .comment(TEST_VAL_BOOK_COMMENT)
                .bookState(TEST_VAL_BOOK_STATE_SHARERABLE)
                .build();
    }

    static Book getBookWithDisSharableState() {
        return Book.builder()
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .bookState(TEST_VAL_BOOK_STATE)
                .comment(TEST_VAL_BOOK_COMMENT)
                .bookState(TEST_VAL_BOOK_STATE_DISSHARERABLE)
                .build();
    }

    static Book getBookWithId() {
        return Book.builder()
                .bookId(TEST_VAL_BOOK_ID)
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .comment(TEST_VAL_BOOK_COMMENT)
                .build();
    }

    static List<Book> getBookList() {
        return Arrays.asList(
                Book.builder()
                        .title(TEST_VAL_BOOK_TITLE)
                        .content(TEST_VAL_BOOK_CONTENT)
                        .writer(TEST_VAL_BOOK_WRITER)
                        .price(TEST_VAL_BOOK_PRICE)
                        .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                        .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                        .isbn(TEST_VAL_BOOK_ISBN)
                        .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                        .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                        .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                        .comment(TEST_VAL_BOOK_COMMENT)
                        .build(),
                Book.builder()
                        .title(TEST_VAL_CHANGE_BOOK_TITLE)
                        .content(TEST_VAL_CHANGE_BOOK_CONTENT)
                        .writer(TEST_VAL_CHANGE_BOOK_WRITER)
                        .price(TEST_VAL_CHANGE_BOOK_PRICE)
                        .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                        .publishDate(TEST_VAL_CHANGE_BOOK_PUBLISH_DATE)
                        .isbn(TEST_VAL_CHANGE_BOOK_ISBN)
                        .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                        .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                        .categoryId(TEST_VAL_CHANGE_BOOK_CATEGORY_ID)
                        .comment(TEST_VAL_BOOK_COMMENT)
                        .build()
        );
    }

    static Category getCategory() {
        return Category.builder()
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .name("adfasdf")
                .description("asdfasdf")
                .build();
    }

    static GetBookListParamDto getGetBookListParamDto() {
        return GetBookListParamDto.builder()
                .title(TEST_VAL_BOOK_TITLE)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .pageable(Pageable.unpaged())
                .build();
    }

    static RegisterBookParamDto getRegisterBookParamDto() {
        return RegisterBookParamDto.builder()
                .title(TEST_VAL_CHANGE_BOOK_TITLE)
                .content(TEST_VAL_CHANGE_BOOK_CONTENT)
                .writer(TEST_VAL_CHANGE_BOOK_WRITER)
                .price(TEST_VAL_CHANGE_BOOK_PRICE)
                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                .publishDate(TEST_VAL_CHANGE_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_CHANGE_BOOK_ISBN)
                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .comment(TEST_VAL_BOOK_COMMENT)
                .build();
    }

    static ChangeBookParamDto getChangeBookParamDto() {
        return ChangeBookParamDto.builder()
                .bookId(TEST_VAL_BOOK_ID)
                .title(TEST_VAL_CHANGE_BOOK_TITLE)
                .content(TEST_VAL_CHANGE_BOOK_CONTENT)
                .writer(TEST_VAL_CHANGE_BOOK_WRITER)
                .price(TEST_VAL_CHANGE_BOOK_PRICE)
                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                .publishDate(TEST_VAL_CHANGE_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_CHANGE_BOOK_ISBN)
                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .comment(TEST_VAL_BOOK_COMMENT)
                .build();
    }

    static ChangeBookParamDto getChangeBookParamDtoRandom(long n) {
        return ChangeBookParamDto.builder()
                .bookId(TEST_VAL_BOOK_ID)
                .title(TEST_VAL_CHANGE_BOOK_TITLE)
                .content(TEST_VAL_CHANGE_BOOK_CONTENT)
                .writer(TEST_VAL_CHANGE_BOOK_WRITER)
                .price(TEST_VAL_CHANGE_BOOK_PRICE)
                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                .publishDate(TEST_VAL_CHANGE_BOOK_PUBLISH_DATE.plusSeconds(n))
                .isbn(TEST_VAL_CHANGE_BOOK_ISBN)
                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .comment(TEST_VAL_BOOK_COMMENT)
                .build();
    }

    static Book getChangedBook() {
        return Book.builder()
                .bookId(TEST_VAL_BOOK_ID)
                .title(TEST_VAL_CHANGE_BOOK_TITLE)
                .content(TEST_VAL_CHANGE_BOOK_CONTENT)
                .writer(TEST_VAL_CHANGE_BOOK_WRITER)
                .price(TEST_VAL_CHANGE_BOOK_PRICE)
                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                .publishDate(TEST_VAL_CHANGE_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_CHANGE_BOOK_ISBN)
                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .comment(TEST_VAL_BOOK_COMMENT)
                .build();
    }

    static Page<BookDto> getBookDtoList() {
        return new PageImpl<>(
                Arrays.asList(
                        BookDto.builder()
                                .title(TEST_VAL_BOOK_TITLE)
                                .content(TEST_VAL_BOOK_CONTENT)
                                .writer(TEST_VAL_BOOK_WRITER)
                                .price(TEST_VAL_BOOK_PRICE)
                                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                                .isbn(TEST_VAL_BOOK_ISBN)
                                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                                .comment(TEST_VAL_BOOK_COMMENT)
                                .build(),
                        BookDto.builder()
                                .title(TEST_VAL_CHANGE_BOOK_TITLE)
                                .content(TEST_VAL_CHANGE_BOOK_CONTENT)
                                .writer(TEST_VAL_CHANGE_BOOK_WRITER)
                                .price(TEST_VAL_CHANGE_BOOK_PRICE)
                                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                                .publishDate(TEST_VAL_CHANGE_BOOK_PUBLISH_DATE)
                                .isbn(TEST_VAL_CHANGE_BOOK_ISBN)
                                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                                .categoryId(TEST_VAL_CHANGE_BOOK_CATEGORY_ID)
                                .comment(TEST_VAL_BOOK_COMMENT)
                                .build()
                )
        );
    }

    static BookDetailDto getBookDetailDto() {
        return BookDetailDto.builder()
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .sharePrice(TEST_VAL_SHARED_BOOK_PRICE)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .startBorrowDay(TEST_VAL_BOOK_START_BORROW_DAY)
                .endBorrowDay(TEST_VAL_BOOK_END_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .comment(TEST_VAL_BOOK_COMMENT)
                .bookState("SHARABLE") // ccxz84 | 2024-05-13 DTO 클래스에 bookState가 String 으로 되어있음
                .build();
    }

    static DeleteBookParamDto getDeleteBookParamDto(UUID bookId) {
        return DeleteBookParamDto.builder()
                .bookId(bookId)
                .build();
    }

    static UpdateBookStateRequest getUpdateBookStateRequest() {
        return UpdateBookStateRequest.newBuilder()
                .setToken(TEST_VAL_ACCESS_TOKE)
                .setBookState(com.ssu.commerce.grpc.BookState.SHARING)
                .addId(TEST_VAL_BOOK_ID.toString())
                .addId(TEST_VAL_ANOTHER_BOOK_ID.toString())
                .build();
    }

    static RentalBookRequest getRentalBookRequest() {
        return RentalBookRequest.newBuilder()
                .setToken(TEST_VAL_ACCESS_TOKE)
                .addId(TEST_VAL_BOOK_ID.toString())
                .addId(TEST_VAL_ANOTHER_BOOK_ID.toString())
                .build();
    }

    static List<Book> getBookListForGrpc() {
        return List.of(
                Book.builder()
                        .bookId(TEST_VAL_BOOK_ID)
                        .build(),
                Book.builder()
                        .bookId(TEST_VAL_ANOTHER_BOOK_ID)
                        .build()
        );
    }

    static List<Book> getBookListForGrpcConflict() {
        return List.of(
                Book.builder()
                        .bookId(TEST_VAL_BOOK_ID)
                        .build(),
                Book.builder()
                        .bookId(TEST_VAL_ANOTHER_BOOK_ID)
                        .bookState(BookState.DISSHAREABLE)
                        .build()
        );
    }
}
