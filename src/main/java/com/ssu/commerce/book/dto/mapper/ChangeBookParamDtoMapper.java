package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.ChangeBookParamDto;
import com.ssu.commerce.book.dto.request.ChangeBookRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        typeConversionPolicy = ReportingPolicy.ERROR,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface ChangeBookParamDtoMapper {
    ChangeBookParamDtoMapper INSTANCE = Mappers.getMapper(ChangeBookParamDtoMapper.class);

    ChangeBookParamDto map(ChangeBookRequestDto requestDto);
}
