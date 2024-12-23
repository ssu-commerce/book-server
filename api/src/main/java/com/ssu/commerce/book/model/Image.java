package com.ssu.commerce.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "image_id", columnDefinition = "CHAR(36)")
    private UUID imageId;

    @Column(name = "book_id", columnDefinition = "CHAR(36)")
    private UUID bookId;
}
