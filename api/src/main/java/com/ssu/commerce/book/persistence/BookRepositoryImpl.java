package com.ssu.commerce.book.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssu.commerce.book.dto.param.query.SelectBookListParamDto;
import com.ssu.commerce.book.model.Book;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;
import java.util.UUID;

import static com.ssu.commerce.book.model.QBook.book;
import static com.ssu.commerce.book.model.QCategory.category;

@Validated
@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Book> selectBookPage(
            @NonNull final SelectBookListParamDto paramDto,
            @NonNull final Pageable pageable
    ) {
        final JPAQuery<Book> jpaQuery = jpaQueryFactory.select(book)
                .from(book)
                .join(category).on(book.categoryId.eq(category.categoryId))
                .where(
                        likeTitle(paramDto.getTitle()),
                        eqCategoryId(paramDto.getCategoryId())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        final JPAQuery<Long> countQuery = jpaQueryFactory.select(book.count())
                .from(book)
                .join(category).on(book.categoryId.eq(category.categoryId))
                .where(
                        likeTitle(paramDto.getTitle()),
                        eqCategoryId(paramDto.getCategoryId())
                );

        return PageableExecutionUtils.getPage(jpaQuery.fetch(), pageable, countQuery::fetchOne);
    }

    private BooleanExpression likeTitle(
            final String title
    ) {
        return StringUtils.isNotEmpty(title)
                ? book.title.containsIgnoreCase(title)
                : null;
    }

    private BooleanExpression eqCategoryId(
            final UUID categoryId
    ) {
        return Objects.nonNull(categoryId)
                ? book.categoryId.eq(categoryId)
                : null;
    }
}
