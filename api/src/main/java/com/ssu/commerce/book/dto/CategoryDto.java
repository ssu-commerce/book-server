package com.ssu.commerce.book.dto;

import com.ssu.commerce.book.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class CategoryDto {
    private UUID categoryId;
    private String name;
    private String description;

    public CategoryDto(Category category) {
        categoryId = category.getCategoryId();
        name = category.getName();
        description = category.getDescription();
    }
}
