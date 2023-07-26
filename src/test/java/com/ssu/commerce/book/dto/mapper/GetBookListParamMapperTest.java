package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.supplier.BookTestDataSupplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class GetBookListParamMapperTest implements BookTestDataSupplier {
    @Test
    void testMap() {
        Assertions.assertEquals(
                BookTestDataSupplier.getGetBookListParamDto(),
                GetBookListParamMapper.INSTANCE.map(
                        TEST_VAL_BOOK_TITLE,
                        TEST_VAL_BOOK_CATEGORY_ID,
                        Pageable.unpaged()
                )
        );
    }
}
