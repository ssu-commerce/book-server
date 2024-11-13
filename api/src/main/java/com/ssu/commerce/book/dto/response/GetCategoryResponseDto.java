package com.ssu.commerce.book.dto.response;

import com.ssu.commerce.book.dto.CategoryDto;
import lombok.*;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryResponseDto {
    private UUID categoryId;
    private String name;
    private String description;

    public GetCategoryResponseDto(CategoryDto categoryDto) {
        categoryId = categoryDto.getCategoryId();
        name = categoryDto.getName();
        description = categoryDto.getDescription();
    }
}
