package com.ssu.commerce.book.dto.param;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Data
@Builder
public class GetBookListParamDto {
    private String title;
    private UUID categoryId;
    private Pageable pageable;
}
