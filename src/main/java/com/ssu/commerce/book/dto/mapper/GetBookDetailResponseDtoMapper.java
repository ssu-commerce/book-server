package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.response.GetBookDetailResponseDto;
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
public interface GetBookDetailResponseDtoMapper {
    GetBookDetailResponseDtoMapper INSTANCE = Mappers.getMapper(GetBookDetailResponseDtoMapper.class);

    GetBookDetailResponseDto map(BookDetailDto bookDetailDto);
}
