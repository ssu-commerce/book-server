package com.ssu.commerce.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
    // category_id, name, description

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "category_id", columnDefinition = "CHAR(36)")
    private UUID categoryId;

    @Column(name = "name", columnDefinition = "CHAR(50) CHARACTER SET UTF8")
    private String name;

    @Column(name = "description", columnDefinition = "CHAR(100) CHARACTER SET UTF8")
    private String description;
}
