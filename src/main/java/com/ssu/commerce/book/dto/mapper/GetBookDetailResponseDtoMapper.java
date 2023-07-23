package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.response.GetBookDetailResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GetBookDetailResponseDtoMapper {
    GetBookDetailResponseDtoMapper INSTANCE = Mappers.getMapper(GetBookDetailResponseDtoMapper.class);

    GetBookDetailResponseDto map(BookDetailDto bookDetailDto);
}
