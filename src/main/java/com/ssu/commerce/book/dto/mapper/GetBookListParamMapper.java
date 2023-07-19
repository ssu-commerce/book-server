package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;

@Mapper(componentModel = "spring")
public interface GetBookListParamMapper {
    GetBookListParamMapper INSTANCE = Mappers.getMapper(GetBookListParamMapper.class);

    GetBookListParamDto map(String title, Long categoryId, Pageable pageable);
}
