package com.ssu.commerce.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "image_id", columnDefinition = "BINARY(16)")
    private UUID imageId;

    @Column(name = "book_id", columnDefinition = "BINARY(16)")
    private UUID bookId;
}
