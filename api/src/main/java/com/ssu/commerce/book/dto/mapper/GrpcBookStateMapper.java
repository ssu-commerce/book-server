package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.config.MapstructConfig;
import com.ssu.commerce.book.constant.code.BookState;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface GrpcBookStateMapper {
    GrpcBookStateMapper INSTANCE = Mappers.getMapper(GrpcBookStateMapper.class);

    @ValueMappings({
            @ValueMapping(source = "DISSHAREABLE", target = "DISSHAREABLE"),
            @ValueMapping(source = "SHARABLE", target = "SHARABLE"),
            @ValueMapping(source = "SHARING", target = "SHARING")
    })
    BookState map(final com.ssu.commerce.grpc.BookState bookState);
}
