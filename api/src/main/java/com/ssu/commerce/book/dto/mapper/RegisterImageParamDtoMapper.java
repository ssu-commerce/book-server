package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.config.MapstructConfig;
import com.ssu.commerce.book.dto.param.RegisterImageParamDto;
import com.ssu.commerce.book.dto.request.RegisterImageRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface RegisterImageParamDtoMapper {
    RegisterImageParamDtoMapper INSTANCE = Mappers.getMapper(RegisterImageParamDtoMapper.class);

    RegisterImageParamDto map(final RegisterImageRequestDto registerImageRequestDto);
}
