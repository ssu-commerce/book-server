package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.RegisterImageParamDto;
import com.ssu.commerce.book.dto.request.RegisterImageRequestDto;
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
public interface RegisterImageParamDtoMapper {
    RegisterImageParamDtoMapper INSTANCE = Mappers.getMapper(RegisterImageParamDtoMapper.class);

    RegisterImageParamDto map(RegisterImageRequestDto registerImageRequestDto);
}
