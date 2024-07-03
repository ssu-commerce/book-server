package com.ssu.commerce.book.dto.param.query;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder
public class SelectBookListParamDto {
    private String title;
    private UUID categoryId;
}
