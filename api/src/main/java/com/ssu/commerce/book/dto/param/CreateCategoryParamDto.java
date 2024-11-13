package com.ssu.commerce.book.dto.param;

import com.ssu.commerce.book.dto.request.CreateCategoryRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class CreateCategoryParamDto {
    @NotBlank
    private String name;

    private String description;

    public CreateCategoryParamDto(CreateCategoryRequestDto requestDto) {
        name = requestDto.getName();
        description = requestDto.getDescription();
    }
}
