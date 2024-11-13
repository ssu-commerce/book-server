package com.ssu.commerce.book.dto.param;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@EqualsAndHashCode
@Builder
public class GetCategoryListParamDto {
    private String name;
    private Pageable pageable;
}
