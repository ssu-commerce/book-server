package com.ssu.commerce.book.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "book_details")
public class BookDetail {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @NotNull
    private BookState bookState;

    @NotNull
    private  String publisher;

    @NotNull
    private String owner;

    @NotNull
    private String region;

}