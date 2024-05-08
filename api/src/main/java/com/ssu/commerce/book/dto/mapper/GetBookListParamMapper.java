package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.config.MapstructConfig;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Mapper(config = MapstructConfig.class)
public interface GetBookListParamMapper {
    GetBookListParamMapper INSTANCE = Mappers.getMapper(GetBookListParamMapper.class);

    GetBookListParamDto map(final String title,final UUID categoryId,final Pageable pageable);
}
