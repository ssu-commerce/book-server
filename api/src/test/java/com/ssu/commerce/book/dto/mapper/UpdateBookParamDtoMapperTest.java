package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.supplier.BookTestDataSupplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UpdateBookParamDtoMapperTest implements BookTestDataSupplier {
    @Test
    void testMap() {
        assertEquals(
                BookTestDataSupplier.getUpdateBookParamDto(),
                UpdateBookParamDtoMapper.INSTANCE.map(BookTestDataSupplier.getChangeBookParamDto())
        );
    }
}
