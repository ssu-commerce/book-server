package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookDetailDtoMapper {
    BookDetailDtoMapper INSTANCE = Mappers.getMapper(BookDetailDtoMapper.class);

    BookDetailDto map(Book book);

}
