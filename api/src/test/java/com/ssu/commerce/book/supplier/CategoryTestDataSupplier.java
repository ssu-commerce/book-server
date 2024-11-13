package com.ssu.commerce.book.supplier;

import com.ssu.commerce.book.dto.CategoryDto;
import com.ssu.commerce.book.dto.param.ChangeCategoryParamDto;
import com.ssu.commerce.book.dto.param.CreateCategoryParamDto;
import com.ssu.commerce.book.dto.param.GetCategoryListParamDto;
import com.ssu.commerce.book.dto.param.query.SelectCategoryListParamDto;
import com.ssu.commerce.book.dto.request.ChangeCategoryRequestDto;
import com.ssu.commerce.book.dto.request.CreateCategoryRequestDto;
import com.ssu.commerce.book.dto.response.GetCategoryResponseDto;
import com.ssu.commerce.book.model.Category;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.UUID;

public interface CategoryTestDataSupplier {
    UUID TEST_VAL_BOOK_CATEGORY_ID = UUID.fromString("578caff7-1c44-4ede-92d2-b6e6cf60c0aa");

    String TEST_VAL_CATEGORY_NAME = "한국소설일반";
    String TEST_VAL_CATEGORY_DESCRIPTION = "소설";
    String TEST_VAL_CATEGORY_DESCRIPTION2 = "라노벨";

    static GetCategoryListParamDto getCategoryListParamDto() {
        return GetCategoryListParamDto.builder()
                .name(TEST_VAL_CATEGORY_NAME)
                .pageable(getPageable())
                .build();
    }

    static SelectCategoryListParamDto getSelectCategoryListParamDto() {
        return SelectCategoryListParamDto.builder()
                .name(TEST_VAL_CATEGORY_NAME)
                .build();
    }

    static Category getCategory() {
        return Category.builder()
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .name(TEST_VAL_CATEGORY_NAME)
                .description(TEST_VAL_CATEGORY_DESCRIPTION)
                .build();
    }

    static Category getCategory2() {
        return Category.builder()
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .name(TEST_VAL_CATEGORY_NAME)
                .description(TEST_VAL_CATEGORY_DESCRIPTION2)
                .build();
    }

    static Page<Category> getCategoryPage() {
        return new PageImpl<>(
                List.of(getCategory()),
                PageRequest.of(0, 10),
                1
        );
    }

    static CategoryDto getCategoryDto() {
        return CategoryDto.builder()
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .name(TEST_VAL_CATEGORY_NAME)
                .description(TEST_VAL_CATEGORY_DESCRIPTION)
                .build();
    }

    static Page<CategoryDto> getCategoryPageDto() {
        return new PageImpl<>(
                List.of(getCategoryDto()),
                getPageable(),
                1
        );
    }

    static CreateCategoryParamDto getCreateCategoryParamDto() {
        return CreateCategoryParamDto.builder()
                .name(TEST_VAL_CATEGORY_NAME)
                .description(TEST_VAL_CATEGORY_DESCRIPTION)
                .build();
    }

    static ChangeCategoryParamDto getChangeCategoryParamDto() {
        return ChangeCategoryParamDto.builder()
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .name(TEST_VAL_CATEGORY_NAME)
                .description(TEST_VAL_CATEGORY_DESCRIPTION2)
                .build();
    }

    static ChangeCategoryRequestDto getChangeCategoryRequestDto() {
        return ChangeCategoryRequestDto.builder()
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .name(TEST_VAL_CATEGORY_NAME)
                .description(TEST_VAL_CATEGORY_DESCRIPTION2)
                .build();
    }

    static Pageable getPageable() {
        return PageRequest.of(0, 10, Sort.unsorted());
    }

    static GetCategoryResponseDto getCategoryResponseDto() {
        return GetCategoryResponseDto.builder()
                .categoryId(TEST_VAL_BOOK_CATEGORY_ID)
                .name(TEST_VAL_CATEGORY_NAME)
                .description(TEST_VAL_CATEGORY_DESCRIPTION)
                .build();
    }

    static CreateCategoryRequestDto getCreateCategoryRequestDto() {
        return CreateCategoryRequestDto.builder()
                .name(TEST_VAL_CATEGORY_NAME)
                .description(TEST_VAL_CATEGORY_DESCRIPTION)
                .build();
    }
}
