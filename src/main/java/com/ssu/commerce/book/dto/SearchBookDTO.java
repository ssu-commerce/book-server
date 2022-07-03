package com.ssu.commerce.book.dto;


import com.ssu.commerce.book.model.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookDTO {
    private int id;
    private String title;
    private String imageUrl;

    public SearchBookDTO(final BookEntity bookEntity){
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.imageUrl = bookEntity.getImageUrl();

    }

}
