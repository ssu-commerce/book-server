package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.dto.param.query.SelectBookListParamDto;
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
public interface SelectBookListParamDtoMapper {
    SelectBookListParamDtoMapper INSTANCE = Mappers.getMapper(SelectBookListParamDtoMapper.class);

    SelectBookListParamDto map(GetBookListParamDto paramDto);
}
