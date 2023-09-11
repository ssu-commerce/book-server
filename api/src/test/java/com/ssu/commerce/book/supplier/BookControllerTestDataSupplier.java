package com.ssu.commerce.book.supplier;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.mapper.BookDtoMapper;
import com.ssu.commerce.book.dto.mapper.GetBookListParamMapper;
import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.dto.request.ChangeBookRequestDto;
import com.ssu.commerce.book.dto.request.DeleteBookRequestDto;
import com.ssu.commerce.book.dto.request.RegisterBookRequestDto;
import com.ssu.commerce.book.dto.response.ChangeBookResponseDto;
import com.ssu.commerce.book.dto.response.GetBookResponseDto;
import com.ssu.commerce.book.model.Book;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface BookControllerTestDataSupplier {
    String TEST_VAL_BOOK_TITLE = "화산귀환";
    String TEST_VAL_BOOK_CONTENT = "대 화산파 13대 제자. 천하삼대검수. 매화검존 청명 " +
            "천하를 혼란에 빠뜨린 고금제일마 천마의 목을 치고 십만대산의 정상에서 영면. 백 년의 시간을 뛰어넘어 아이의 몸으로 다시 살아나다. " +
            "그런데...... 뭐? 화산이 망해? 이게 뭔 개소리야!? " +
            "망했으면 살려야 하는 게 인지상정.";
    String TEST_VAL_BOOK_WRITER = "비가";
    Long TEST_VAL_BOOK_PRICE = 15120L;
    UUID TEST_VAL_BOOK_OWNER_ID = UUID.fromString("b794d677-aa71-432f-8016-dbbf4cfe09c7");
    LocalDateTime TEST_VAL_BOOK_PUBLISH_DATE = LocalDateTime.of(2023,7,23,0,0,0);
    String TEST_VAL_BOOK_ISBN = "9791198173898";
    Long TEST_VAL_BOOK_MAX_BORROW_DAY = 15L;

    UUID TEST_VAL_BOOK_ID = UUID.fromString("1755e96b-bc66-47a8-981d-92e03c861b3c");
    UUID TEST_VAL_OWNER_ID = UUID.fromString("8ad8c2eb-ee9a-43f1-be82-0e0d8ddf70b1");

    UUID TEST_VAL_BOOK_CATEGORY_ID = UUID.fromString("578caff7-1c44-4ede-92d2-b6e6cf60c0aa");


    UUID TEST_VAL_ANOTHER_BOOK_DTO_ID = UUID.fromString("e92a4553-9e43-401f-9d14-cfce1d90164b");
    UUID TEST_VAL_ANOTHER_OWNER_ID = UUID.fromString("6fa9bc31-591b-40b9-b498-48fb6392c75a");

    static Book getBook() {
        return Book.builder()
                .id(TEST_VAL_BOOK_ID)
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .ownerId(TEST_VAL_OWNER_ID)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .maxBorrowDay(TEST_VAL_BOOK_MAX_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .build();
    }

    static Book getAnotherBook() {
        return Book.builder()
                .id(TEST_VAL_ANOTHER_BOOK_DTO_ID)
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .ownerId(TEST_VAL_ANOTHER_OWNER_ID)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .maxBorrowDay(TEST_VAL_BOOK_MAX_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .build();
    }

    static BookDetailDto getBookDetailDto() {
        return BookDetailDto.builder()
                .id(TEST_VAL_BOOK_ID)
                .title(TEST_VAL_BOOK_TITLE)
                .content(TEST_VAL_BOOK_CONTENT)
                .writer(TEST_VAL_BOOK_WRITER)
                .price(TEST_VAL_BOOK_PRICE)
                .ownerId(TEST_VAL_OWNER_ID)
                .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                .isbn(TEST_VAL_BOOK_ISBN)
                .maxBorrowDay(TEST_VAL_BOOK_MAX_BORROW_DAY)
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .build();
    }

    static RegisterBookRequestDto getRegisterBookRequestDto() {
        return RegisterBookRequestDto.builder()
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

    static ChangeBookResponseDto getChangeBookResponseDto() {
        return ChangeBookResponseDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    static DeleteBookRequestDto getDeleteBookRequestDto() {
        return DeleteBookRequestDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    static List<GetBookResponseDto> getGetBookResponseDto() {
        return Arrays.asList(
                GetBookResponseDto.builder()
                        .id(TEST_VAL_BOOK_ID)
                        .title(TEST_VAL_BOOK_TITLE)
                        .content(TEST_VAL_BOOK_CONTENT)
                        .writer(TEST_VAL_BOOK_WRITER)
                        .price(TEST_VAL_BOOK_PRICE)
                        .ownerId(TEST_VAL_OWNER_ID)
                        .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                        .isbn(TEST_VAL_BOOK_ISBN)
                        .maxBorrowDay(TEST_VAL_BOOK_MAX_BORROW_DAY)
                        .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                        .build(),
                GetBookResponseDto.builder()
                        .id(TEST_VAL_ANOTHER_BOOK_DTO_ID)
                        .title(TEST_VAL_BOOK_TITLE)
                        .content(TEST_VAL_BOOK_CONTENT)
                        .writer(TEST_VAL_BOOK_WRITER)
                        .price(TEST_VAL_BOOK_PRICE)
                        .ownerId(TEST_VAL_ANOTHER_OWNER_ID)
                        .publishDate(TEST_VAL_BOOK_PUBLISH_DATE)
                        .isbn(TEST_VAL_BOOK_ISBN)
                        .maxBorrowDay(TEST_VAL_BOOK_MAX_BORROW_DAY)
                        .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                        .build()
        );
    }

    static GetBookListParamDto getGetBookListParamDto() {

        return GetBookListParamMapper.INSTANCE.map(null, null, PageRequest.of(0, 20, Sort.by("id")));
    }

    static Page<BookDto> getBookDtoPage(List<Book> bookDtoList) {
        return new PageImpl<>(
                BookDtoMapper.INSTANCE.mapToList(bookDtoList),
                PageRequest.of(0, 20, Sort.unsorted()),
                bookDtoList.size()
        );
    }

    static ChangeBookRequestDto getChangeBookRequestDto() {
        return ChangeBookRequestDto.builder()
                .id(TEST_VAL_BOOK_ID)
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
}
