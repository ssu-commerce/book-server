package com.ssu.commerce.book.dto.param.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectBookListParamDto {
    private String title;
    private Long categoryId;
}
