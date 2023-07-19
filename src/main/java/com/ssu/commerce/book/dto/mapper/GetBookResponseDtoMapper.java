package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.response.GetBookResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GetBookResponseDtoMapper {
    GetBookResponseDtoMapper INSTANCE = Mappers.getMapper(GetBookResponseDtoMapper.class);

    default List<GetBookResponseDto> mapToList(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    GetBookResponseDto map(BookDto bookDto);
}
