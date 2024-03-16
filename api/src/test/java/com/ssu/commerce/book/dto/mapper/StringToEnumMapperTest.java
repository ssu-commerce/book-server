package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.constant.code.BookState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class StringToEnumMapperTest {
    @Test
    void testMapStringToEnum() {
        final StringToEnumMapper mapper = new StringToEnumMapper();

        assertEquals(
                BookState.SHARABLE,
                mapper.mapStringToEnum(BookState.class, "SHARABLE")
        );
    }

    @Test
    void testMapStringToEnumNotExist() {
        final StringToEnumMapper mapper = new StringToEnumMapper();

        assertNull(
                mapper.mapStringToEnum(BookState.class, "TEST")
        );
    }
}
