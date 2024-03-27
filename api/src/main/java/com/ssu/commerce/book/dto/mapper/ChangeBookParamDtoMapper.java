package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.config.MapstructConfig;
import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.request.ChangeBookRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface ChangeBookParamDtoMapper {
    ChangeBookParamDtoMapper INSTANCE = Mappers.getMapper(ChangeBookParamDtoMapper.class);

    ChangeBookParamDto map(final ChangeBookRequestDto requestDto);
}
