package com.ssu.commerce.book.controller;

import com.ssu.commerce.book.dto.CategoryDto;
import com.ssu.commerce.book.dto.param.ChangeCategoryParamDto;
import com.ssu.commerce.book.dto.param.CreateCategoryParamDto;
import com.ssu.commerce.book.dto.param.GetCategoryListParamDto;
import com.ssu.commerce.book.dto.request.ChangeCategoryRequestDto;
import com.ssu.commerce.book.dto.request.CreateCategoryRequestDto;
import com.ssu.commerce.book.dto.response.ChangeCategoryResponseDto;
import com.ssu.commerce.book.dto.response.CreateCategoryResponseDto;
import com.ssu.commerce.book.dto.response.DeleteCategoryResponseDto;
import com.ssu.commerce.book.dto.response.GetCategoryResponseDto;
import com.ssu.commerce.book.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public Page<GetCategoryResponseDto> getCategoryList(
            @Nullable @RequestParam final String name,
            final Pageable pageable
    ) {
        log.debug("[getCategoryList]name={}", name);

        final Page<CategoryDto> categoryPage = categoryService.getCategoryList(
                GetCategoryListParamDto.builder()
                        .name(name)
                        .pageable(pageable)
                        .build()
        );

        return categoryPage.map(GetCategoryResponseDto::new);
    }

    @PostMapping
    public CreateCategoryResponseDto registerCategory(
            @Valid @RequestBody final CreateCategoryRequestDto requestDto
    ) {
        log.debug("[registerCategory]requestDto={}", requestDto);

        return CreateCategoryResponseDto.builder()
                .categoryId(categoryService.registerCategory(new CreateCategoryParamDto(requestDto)))
                .build();
    }

    @PutMapping
    public ChangeCategoryResponseDto changeCategory(
            @Valid @RequestBody final ChangeCategoryRequestDto requestDto
    ) {
        log.debug("[changeCategory]requestDto={}", requestDto);

        return ChangeCategoryResponseDto.builder()
                .categoryId(
                        categoryService.changeCategory(new ChangeCategoryParamDto(requestDto))
                                .getCategoryId()
                )
                .build();
    }

    @DeleteMapping("/{id}")
    public DeleteCategoryResponseDto deleteCategory(
            @Valid @PathVariable final UUID id
    ) {
        log.debug("[deleteCategory]id={}", id);

        return DeleteCategoryResponseDto.builder()
                .categoryId(categoryService.deleteCategory(id))
                .build();
    }
}
