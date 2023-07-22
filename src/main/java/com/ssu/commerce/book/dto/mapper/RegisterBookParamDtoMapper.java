package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.dto.request.RegisterBookRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegisterBookParamDtoMapper {
    RegisterBookParamDtoMapper INSTANCE = Mappers.getMapper(RegisterBookParamDtoMapper.class);

    RegisterBookParamDto map(RegisterBookRequestDto requestDto);
}
