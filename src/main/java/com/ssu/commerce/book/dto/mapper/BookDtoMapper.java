package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        typeConversionPolicy = ReportingPolicy.ERROR,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface BookDtoMapper {
    BookDtoMapper INSTANCE = Mappers.getMapper(BookDtoMapper.class);

    default List<BookDto> mapToList(List<Book> bookList) {
        return bookList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    BookDto map(Book book);
}
