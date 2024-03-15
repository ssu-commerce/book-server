package com.ssu.commerce.book.dto.mapper;

public class StringToEnumMapper {
    public <E extends Enum<E>> E mapStringToEnum(final Class<E> enumType,final String value) {
        if (value == null) {
            return null;
        }
        return Enum.valueOf(enumType, value.toUpperCase());
    }
}
