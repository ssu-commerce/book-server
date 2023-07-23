package com.ssu.commerce.book.persistence;

import com.ssu.commerce.book.dto.param.query.SelectBookListParamDto;
import com.ssu.commerce.book.model.Book;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryCustom {
    Page<Book> selectBookPage(
            @NonNull @Valid final SelectBookListParamDto paramDto,
            @NonNull final Pageable pageable
    );
}
