package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.dto.response.RegisterImageResponseDto;
import com.ssu.commerce.book.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        typeConversionPolicy = ReportingPolicy.ERROR,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface RegisterImageResponseDtoMapper {
    RegisterImageResponseDtoMapper INSTANCE = Mappers.getMapper(RegisterImageResponseDtoMapper.class);

    List<RegisterImageResponseDto> mapList(List<Image> images);

    RegisterImageResponseDto map(Image image);
}
