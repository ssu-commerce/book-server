package com.ssu.commerce.book.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssu.commerce.book.dto.param.query.SelectCategoryListParamDto;
import com.ssu.commerce.book.model.Category;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import static com.ssu.commerce.book.model.QCategory.category;

@Validated
@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Category> selectCategoryPage(final SelectCategoryListParamDto paramDto, final Pageable pageable) {
        final JPAQuery<Category> jpaQuery = jpaQueryFactory.select(category)
                .from(category)
                .where(
                        likeName(paramDto.getName())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        final JPAQuery<Long> countQuery = jpaQueryFactory.select(category.count())
                .from(category)
                .where(
                        likeName(paramDto.getName())
                );

        return PageableExecutionUtils.getPage(jpaQuery.fetch(), pageable, countQuery::fetchOne);
    }

    private BooleanExpression likeName(
            final String name
    ) {
        return StringUtils.isNotEmpty(name)
                ? category.name.containsIgnoreCase(name)
                : null;
    }
}
