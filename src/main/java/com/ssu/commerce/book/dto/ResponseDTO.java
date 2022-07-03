package com.ssu.commerce.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;

@Builder
@AllArgsConstructor
public class ResponseDTO<T> {
    private String error;
    private List<T> data;
}
