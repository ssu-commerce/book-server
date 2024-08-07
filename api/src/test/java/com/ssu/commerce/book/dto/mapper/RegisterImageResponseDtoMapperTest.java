package com.ssu.commerce.book.dto.mapper;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import com.ssu.commerce.book.dto.response.RegisterImageResponseDto;
import com.ssu.commerce.book.model.Image;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RegisterImageResponseDtoMapperTest {

    private RegisterImageResponseDtoMapperImpl mapper;
    private List<Image> images;

    @BeforeEach
    void setUp() {
        mapper = new RegisterImageResponseDtoMapperImpl();
        images =
                Arrays.asList(
                        Image.builder()
                                .imageId(UUID.randomUUID())
                                .bookId(UUID.randomUUID())
                                .build(),

                        Image.builder()
                                .imageId(UUID.randomUUID())
                                .bookId(UUID.randomUUID())
                                .build(),

                        Image.builder()
                                .imageId(UUID.randomUUID())
                                .bookId(UUID.randomUUID())
                                .build()
                );
    }

    @Test
    void map_GivenValidImage_ShouldBeReturnRegisterImageResponseDto() {
        Image.ImageBuilder builder = Image.builder();
        builder.bookId(UUID.randomUUID());
        builder.imageId(UUID.randomUUID());

        Image image = builder.build();

        RegisterImageResponseDto registerImageResponseDto = mapper.map(image);

        assertNotNull(registerImageResponseDto);
        assertEquals(registerImageResponseDto.getImageId(), image.getImageId());
    }

    @Test
    void map_GivenNull_ShouldReturnDtoWithNullValues() {
        RegisterImageResponseDto dto = mapper.map(null);

        assertNotNull(dto);
        assertNull(dto.getImageId());
    }

    @Test
    void mapList_GivenValidListImage_ShouldBeReturnListRegisterImageResponseDto() {
        List<RegisterImageResponseDto> registerImageResponseDtoList = mapper.mapToList(this.images);

        Iterator registerImageResponseIterator = registerImageResponseDtoList.iterator();
        Iterator imageIterator = this.images.iterator();

        assertNotNull(registerImageResponseDtoList);
        assertEquals(registerImageResponseDtoList.size(), this.images.size());

        while(registerImageResponseIterator.hasNext() && imageIterator.hasNext()) {
            RegisterImageResponseDto dto = (RegisterImageResponseDto)registerImageResponseIterator.next();
            Image image = (Image)imageIterator.next();
            assertEquals(dto.getImageId(), image.getImageId());
        }
    }

    @Test
    void mapList_GivenNull_ShouldReturnDtoWithNullValues() {
        List<RegisterImageResponseDto> dto = mapper.mapToList(null);

        assertNotNull(dto);
        assertEquals(dto.size(), 0);
    }
}
