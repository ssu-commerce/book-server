package com.ssu.commerce.book.dto.mapper;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class StringToEnumMapper {
    @Nullable
    public <E extends Enum<E>> E mapStringToEnum(
            @NotNull final Class<E> enumType,
            @NotNull final String value
    ) {
        return EnumSet.allOf(enumType)
                .stream()
                .filter(x -> x.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
