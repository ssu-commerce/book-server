package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.config.MapstructConfig;
import com.ssu.commerce.book.dto.response.RegisterImageResponseDto;
import com.ssu.commerce.book.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface RegisterImageResponseDtoMapper {
    RegisterImageResponseDtoMapper INSTANCE = Mappers.getMapper(RegisterImageResponseDtoMapper.class);

    List<RegisterImageResponseDto> mapToList(final List<Image> imageList);

    RegisterImageResponseDto map(final Image image);
}
