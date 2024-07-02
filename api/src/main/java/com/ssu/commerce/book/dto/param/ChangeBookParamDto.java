package com.ssu.commerce.book.dto.param;

import com.ssu.commerce.book.lock.AbstractLockableObject;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ChangeBookParamDto extends AbstractLockableObject {

    @NotEmpty
    private UUID bookId;

    @NotEmpty
    private String title;

    private String content;

    @NotEmpty
    private String writer;

    @NotNull
    private Long price;

    @NotNull
    private Long sharePrice;

    private String comment;

    @NotNull
    private LocalDateTime startBorrowDay;

    @NotNull
    private LocalDateTime endBorrowDay;

    @NotEmpty
    private UUID ownerId;

    private LocalDateTime publishDate;

    @NotEmpty
    private String isbn;

    @NotNull
    private UUID categoryId;
}
