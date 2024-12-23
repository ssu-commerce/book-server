package com.ssu.commerce.book.service;

import com.ssu.commerce.book.model.Category;
import com.ssu.commerce.book.persistence.CategoryRepository;
import com.ssu.commerce.book.supplier.CategoryTestDataSupplier;
import com.ssu.commerce.core.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest implements CategoryTestDataSupplier {
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void getCategoryList() {
        when(
                categoryRepository.selectCategoryPage(
                        CategoryTestDataSupplier.getSelectCategoryListParamDto(),
                        CategoryTestDataSupplier.getPageable()
                )
        ).thenReturn(CategoryTestDataSupplier.getCategoryPage());

        assertThat(categoryService.getCategoryList(CategoryTestDataSupplier.getCategoryListParamDto()))
                .isEqualTo(CategoryTestDataSupplier.getCategoryPageDto());

        verify(categoryRepository).selectCategoryPage(
                CategoryTestDataSupplier.getSelectCategoryListParamDto(),
                CategoryTestDataSupplier.getPageable()
        );
    }

    @Test
    void registerCategory() {
        final Category category = Category.builder()
                .name(TEST_VAL_CATEGORY_NAME)
                .description(TEST_VAL_CATEGORY_DESCRIPTION)
                .build();

        when(categoryRepository.save(category)).thenReturn(CategoryTestDataSupplier.getCategory());

        assertThat(categoryService.registerCategory(CategoryTestDataSupplier.getCreateCategoryParamDto()))
                .isEqualTo(TEST_VAL_BOOK_CATEGORY_ID);

        verify(categoryRepository).save(category);
    }

    @Test
    void changeCategory() {
        when(categoryRepository.findById(TEST_VAL_BOOK_CATEGORY_ID))
                .thenReturn(Optional.of(CategoryTestDataSupplier.getCategory()));

        assertThat(categoryService.changeCategory(CategoryTestDataSupplier.getChangeCategoryParamDto()))
                .isEqualTo(CategoryTestDataSupplier.getCategory2());

        verify(categoryRepository).findById(TEST_VAL_BOOK_CATEGORY_ID);
    }

    @Test
    void deleteCategory() {
        when(categoryRepository.findById(TEST_VAL_BOOK_CATEGORY_ID))
                .thenReturn(Optional.of(CategoryTestDataSupplier.getCategory()));

        assertThat(categoryService.deleteCategory(TEST_VAL_BOOK_CATEGORY_ID))
                .isEqualTo(TEST_VAL_BOOK_CATEGORY_ID);

        verify(categoryRepository).deleteById(TEST_VAL_BOOK_CATEGORY_ID);
    }

    @Test
    void getCategoryException() {
        when(categoryRepository.findById(TEST_VAL_BOOK_CATEGORY_ID))
                .thenReturn(Optional.empty());

        final NotFoundException notFoundException = assertThrows(
                NotFoundException.class,
                () -> categoryService.deleteCategory(TEST_VAL_BOOK_CATEGORY_ID)
        );

        assertAll(
                "메소드 호출 결과를 검증합니다.",
                () -> assertThat(notFoundException.getMessage())
                        .isEqualTo("category not found; categoryId=578caff7-1c44-4ede-92d2-b6e6cf60c0aa"),
                () -> assertThat(notFoundException.getErrorCode())
                        .isEqualTo("BOOK_002")
        );
    }
}
