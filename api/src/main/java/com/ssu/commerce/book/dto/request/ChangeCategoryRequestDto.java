package com.ssu.commerce.book.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeCategoryRequestDto {
    @NotNull
    private UUID categoryId;

    private String name;

    private String description;
}
