package com.ssu.commerce.book.persistence;

import com.ssu.commerce.book.dto.param.query.SelectCategoryListParamDto;
import com.ssu.commerce.book.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface CategoryRepositoryCustom {
    Page<Category> selectCategoryPage(
            @NotNull final SelectCategoryListParamDto paramDto,
            @NotNull final Pageable pageable
    );
}
