package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.RegisterBookParamDto;
import com.ssu.commerce.book.dto.request.RegisterBookRequestDto;
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
public interface RegisterBookParamDtoMapper {
    RegisterBookParamDtoMapper INSTANCE = Mappers.getMapper(RegisterBookParamDtoMapper.class);

    RegisterBookParamDto map(RegisterBookRequestDto requestDto);
}
