package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.config.MapstructConfig;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface BookDtoMapper {
    BookDtoMapper INSTANCE = Mappers.getMapper(BookDtoMapper.class);

    List<BookDto> mapToList(final List<Book> bookList);

    BookDto map(final Book book);
}
