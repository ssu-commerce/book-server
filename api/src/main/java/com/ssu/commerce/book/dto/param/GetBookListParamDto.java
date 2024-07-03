package com.ssu.commerce.book.dto.param;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder
public class GetBookListParamDto {
    private String title;
    private UUID categoryId;
    private Pageable pageable;
}
