package com.ssu.commerce.book.dto.response;

import lombok.Data;

import javax.persistence.Column;

@Data
public class GetBookInfoResponseDto {
    private String title;

    private String imageUrl;

    private Long price;

    private Long ownerId;

    public GetBookInfoResponseDto(String title, String imageUrl, Long price, Long ownerId) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
        this.ownerId = ownerId;
    }
}
