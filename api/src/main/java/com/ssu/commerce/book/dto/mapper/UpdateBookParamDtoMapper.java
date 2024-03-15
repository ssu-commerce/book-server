package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.config.MapstructConfig;
import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.param.query.UpdateBookParamDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface UpdateBookParamDtoMapper {
    UpdateBookParamDtoMapper INSTANCE = Mappers.getMapper(UpdateBookParamDtoMapper.class);

    UpdateBookParamDto map(final ChangeBookParamDto paramDto);
}
