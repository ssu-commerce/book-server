package com.ssu.commerce.book.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookRequestDto {
    @NotEmpty
    private String title;

    private String content;

    @NotEmpty
    private String writer;

    @NotNull
    private Long price;

    private LocalDateTime publishDate;

    @NotEmpty
    private String isbn;

    @NotNull
    private Long maxBorrowDay;

    @NotNull
    private UUID categoryId;

    // 일별 대여료
    // maxBorrowDay 를 없앤다. 대여자의 자유도를 위해 최대 대여기간을 정하지 말고, 1일 대여비를 정하고 대여자의 결정에 따라 대여기간을 정한다. comment 로 이를 대채한다.
    // 소유자는 몇일부터 몇일까지 이 책을 대여해 줄 것인지 정한다.
    // 가격은 책 가격, 1일 대여 가격이 필요하다.
}
