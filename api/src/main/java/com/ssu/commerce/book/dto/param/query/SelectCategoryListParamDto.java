package com.ssu.commerce.book.dto.param.query;

import com.ssu.commerce.book.dto.param.GetCategoryListParamDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class SelectCategoryListParamDto {
    private String name;

    public SelectCategoryListParamDto(GetCategoryListParamDto paramDto) {
        name = paramDto.getName();
    }
}
