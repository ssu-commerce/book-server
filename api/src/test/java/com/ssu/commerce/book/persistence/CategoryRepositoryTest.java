package com.ssu.commerce.book.persistence;

import com.ssu.commerce.book.config.QuerydslConfig;
import com.ssu.commerce.book.model.Category;
import com.ssu.commerce.book.supplier.CategoryTestDataSupplier;
import com.ssu.commerce.core.jpa.config.JpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
@Import({JpaConfig.class, QuerydslConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest implements CategoryTestDataSupplier {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @Test
    void selectCategoryPage() {
        final Category category = categoryRepository.save(
                Category.builder()
                        .name(TEST_VAL_CATEGORY_NAME)
                        .description(TEST_VAL_CATEGORY_DESCRIPTION)
                        .build()
        );

        final Page<Category> categoryPage = categoryRepository.selectCategoryPage(
                CategoryTestDataSupplier.getSelectCategoryListParamDto(),
                CategoryTestDataSupplier.getPageable()
        );

        assertAll(
                "메소드 호출 결과를 검증합니다.",
                () -> assertThat(categoryPage.getContent()).isNotNull(),
                () -> assertThat(categoryPage.getTotalPages()).isEqualTo(1),
                () -> assertThat(categoryPage.getPageable()).isEqualTo(CategoryTestDataSupplier.getPageable()),
                () -> assertThat(categoryPage.getContent())
                        .isEqualTo(
                                List.of(
                                        Category.builder()
                                                .categoryId(category.getCategoryId())
                                                .name(TEST_VAL_CATEGORY_NAME)
                                                .description(TEST_VAL_CATEGORY_DESCRIPTION)
                                                .build()
                                )
                        )
        );
    }

    @Test
    void likeNmaIsNull() {
        assertThat(categoryRepositoryImpl.likeName(null)).isNull();
    }
}
