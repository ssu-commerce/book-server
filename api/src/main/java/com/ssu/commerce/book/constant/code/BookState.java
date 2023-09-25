package com.ssu.commerce.book.constant.code;

public enum BookState {
    REGISTERED, // 등록됨
    LOAN, // 대여
    LOOSE, // 분실
    DAMAGE, // 손상
    LOAN_PROCESSING, // 대여 진행중
    RETURN // 반납
}
