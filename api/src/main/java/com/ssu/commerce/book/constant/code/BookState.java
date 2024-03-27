package com.ssu.commerce.book.constant.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BookState {
    SHARABLE("SHAREABLE", "대여 가능 상태"),
    DISSHAREABLE("DISSHAREABLE", "대여 불가능 상태(대여가능 날짜 미등록 등의 이유)"),
    SHARING("SHARING", "대여중인 상태");

    private String code;
    private String description;
}
