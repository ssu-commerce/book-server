package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.constant.code.BookState;
import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.model.Book;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        typeConversionPolicy = ReportingPolicy.ERROR,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "bookState", ignore = true)
    Book map(RegisterBookParamDto paramDto);

    @AfterMapping
    default void setDefaultValues(@MappingTarget Book book) {
        book.setBookState(BookState.REGISTERED);
    }
}
