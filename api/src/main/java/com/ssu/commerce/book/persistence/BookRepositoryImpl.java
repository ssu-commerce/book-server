package com.ssu.commerce.book.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssu.commerce.book.dto.param.query.SelectBookListParamDto;
import com.ssu.commerce.book.dto.param.query.UpdateBookParamDto;
import com.ssu.commerce.book.model.Book;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.List;
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
                .innerJoin(category).on(book.categoryId.eq(category.id))
                .where(
                        likeTitle(paramDto.getTitle()),
                        eqCategoryId(paramDto.getCategoryId())
                );

        if (pageable.isPaged()) {
            jpaQuery.offset(pageable.getOffset())
                    .limit(pageable.getPageSize());
        }

        final List<Book> result = jpaQuery.fetch();

        final JPAQuery<Long> countQuery = jpaQueryFactory.select(book.count())
                .from(book)
                .innerJoin(category).on(book.categoryId.eq(category.id))
                .where(
                        likeTitle(paramDto.getTitle()),
                        eqCategoryId(paramDto.getCategoryId())
                );

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    @Override
    public Book changeBook(@NonNull UpdateBookParamDto paramDto) {
        jpaQueryFactory.update(book)
                .where(
                        book.id.eq(paramDto.getId()),
                        eqCategoryId(paramDto.getCategoryId())
                )
                .set(book.title, paramDto.getTitle())
                .set(book.content, paramDto.getContent())
                .set(book.price, paramDto.getPrice())
                .set(book.publishDate, paramDto.getPublishDate())
                .set(book.isbn, paramDto.getIsbn())
                .set(book.maxBorrowDay, paramDto.getMaxBorrowDay())
                .execute();

        return jpaQueryFactory.select(book)
                .where(book.id.eq(paramDto.getId()))
                .fetchOne();
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
