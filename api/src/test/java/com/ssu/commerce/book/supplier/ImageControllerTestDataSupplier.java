package com.ssu.commerce.book.supplier;

import com.ssu.commerce.book.dto.request.RegisterImageRequestDto;
import com.ssu.commerce.book.dto.response.RegisterImageResponseDto;
import com.ssu.commerce.book.model.Image;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface ImageControllerTestDataSupplier {

    static List<RegisterImageResponseDto> getRegisterImageResponseDtoList() {
        return Arrays.asList(
                RegisterImageResponseDto.builder().id(UUID.randomUUID()).build(),
                RegisterImageResponseDto.builder().id(UUID.randomUUID()).build()
        );
    }

    static List<Image> getImageList() {
        return Arrays.asList(
                Image.builder().id(UUID.randomUUID()).bookId(UUID.randomUUID()).build(),
                Image.builder().id(UUID.randomUUID()).bookId(UUID.randomUUID()).build()
        );
    }
    static RegisterImageRequestDto getRegisterImageRequestDto() {
        return RegisterImageRequestDto.builder()
                .bookId(UUID.randomUUID())
                .build();
    }
}
