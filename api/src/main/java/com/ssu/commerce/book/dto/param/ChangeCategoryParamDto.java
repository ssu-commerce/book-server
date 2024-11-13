package com.ssu.commerce.book.dto.param;

import com.ssu.commerce.book.dto.request.ChangeCategoryRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class ChangeCategoryParamDto {
    @NotNull
    private UUID categoryId;

    private String name;

    private String description;

    public ChangeCategoryParamDto(ChangeCategoryRequestDto requestDto) {
        categoryId = requestDto.getCategoryId();
        name = requestDto.getName();
        description = requestDto.getDescription();
    }
}
