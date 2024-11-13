package com.ssu.commerce.book.service;

import com.ssu.commerce.book.dto.CategoryDto;
import com.ssu.commerce.book.dto.param.ChangeCategoryParamDto;
import com.ssu.commerce.book.dto.param.CreateCategoryParamDto;
import com.ssu.commerce.book.dto.param.GetCategoryListParamDto;
import com.ssu.commerce.book.dto.param.query.SelectCategoryListParamDto;
import com.ssu.commerce.book.model.Category;
import com.ssu.commerce.book.persistence.CategoryRepository;
import com.ssu.commerce.core.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Page<CategoryDto> getCategoryList(@NotNull final GetCategoryListParamDto paramDto) {
        final Page<Category> categoryPage = categoryRepository.selectCategoryPage(
                new SelectCategoryListParamDto(paramDto),
                paramDto.getPageable()
        );

        return categoryPage.map(CategoryDto::new);
    }

    @Transactional
    public UUID registerCategory(@NotNull final CreateCategoryParamDto paramDto) {
        return categoryRepository.save(new Category(paramDto))
                .getCategoryId();
    }

    @Transactional
    public Category changeCategory(@NotNull @Valid ChangeCategoryParamDto paramDto) {
        final UUID categoryId = paramDto.getCategoryId();
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("category not found; categoryId=%s", categoryId),
                        "BOOK_002"
                ));

        return category.update(paramDto);
    }

    @Transactional
    public UUID deleteCategory(@NotNull final UUID id) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("category not found; categoryId=%s", id),
                        "BOOK_002"
                ));


        // TODO CREATE -> DELETE
        categoryRepository.deleteById(id);

        return category.getCategoryId();
    }
}
