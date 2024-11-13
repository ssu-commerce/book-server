package com.ssu.commerce.book.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryResponseDto {
    private UUID categoryId;
}
