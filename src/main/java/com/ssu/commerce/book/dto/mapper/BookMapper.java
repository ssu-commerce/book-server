package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book map(RegisterBookParamDto paramDto);
}
