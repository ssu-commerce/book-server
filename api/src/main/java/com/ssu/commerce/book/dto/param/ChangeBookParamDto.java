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
    private UUID id;

    @NotEmpty
    private String title;

    private String content;

    @NotEmpty
    private String writer;

    @NotNull
    private Long price;

    @NotNull
    private Long sharePrice;

    private LocalDateTime publishDate;

    @NotEmpty
    private String isbn;

    @NotNull
    private LocalDateTime startBorrowDay;

    @NotNull
    private LocalDateTime endBorrowDay;

    @NotNull
    private UUID categoryId;
}
