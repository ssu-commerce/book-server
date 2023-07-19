package com.ssu.commerce.book.dto.param;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@Builder
public class GetBookListParamDto {
    private String title;
    private Long categoryId;
    private Pageable pageable;
}
