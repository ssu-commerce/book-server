package com.ssu.commerce.book.config;

import com.ssu.commerce.book.dto.mapper.StringToEnumMapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
        uses = {
                StringToEnumMapper.class
        },
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        typeConversionPolicy = ReportingPolicy.ERROR,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public class MapstructConfig {
}
