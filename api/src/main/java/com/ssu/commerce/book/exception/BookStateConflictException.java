package com.ssu.commerce.book.exception;

import com.ssu.commerce.core.error.SsuCommerceException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatus;

public class BookStateConflictException extends SsuCommerceException {
    public BookStateConflictException(@Nullable String errorCode, @NotNull String message) {
        super(HttpStatus.CONFLICT.value(), errorCode, message);
    }
}
