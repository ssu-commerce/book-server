package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        typeConversionPolicy = ReportingPolicy.ERROR,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface GetBookListParamMapper {
    GetBookListParamMapper INSTANCE = Mappers.getMapper(GetBookListParamMapper.class);

    GetBookListParamDto map(String title, UUID categoryId, Pageable pageable);
}
