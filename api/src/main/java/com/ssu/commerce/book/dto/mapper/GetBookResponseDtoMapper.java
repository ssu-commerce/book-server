package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.config.MapstructConfig;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.response.GetBookResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface GetBookResponseDtoMapper {
    GetBookResponseDtoMapper INSTANCE = Mappers.getMapper(GetBookResponseDtoMapper.class);

    List<GetBookResponseDto> mapToList(final List<BookDto> bookDtoList);

    GetBookResponseDto map(final BookDto bookDto);
}
