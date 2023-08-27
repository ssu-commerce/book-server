package com.ssu.commerce.book.dto.param.query;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SelectBookListParamDto {
    private String title;
    private UUID categoryId;
}
