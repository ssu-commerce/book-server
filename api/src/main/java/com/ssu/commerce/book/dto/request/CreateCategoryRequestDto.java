package com.ssu.commerce.book.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequestDto {
    @NotBlank
    private String name;

    private String description;
}
