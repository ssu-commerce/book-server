package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.config.MapstructConfig;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.dto.param.query.SelectBookListParamDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface SelectBookListParamDtoMapper {
    SelectBookListParamDtoMapper INSTANCE = Mappers.getMapper(SelectBookListParamDtoMapper.class);

    SelectBookListParamDto map(final GetBookListParamDto paramDto);
}
