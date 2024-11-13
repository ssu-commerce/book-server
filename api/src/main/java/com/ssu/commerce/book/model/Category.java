package com.ssu.commerce.book.model;

import com.ssu.commerce.book.dto.param.ChangeCategoryParamDto;
import com.ssu.commerce.book.dto.param.CreateCategoryParamDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "category_id", columnDefinition = "CHAR(36)")
    private UUID categoryId;

    @Column(name = "name", columnDefinition = "CHAR(50) CHARACTER SET UTF8")
    private String name;

    @Column(name = "description", columnDefinition = "CHAR(100) CHARACTER SET UTF8")
    private String description;

    public Category(CreateCategoryParamDto paramDto) {
        name = paramDto.getName();
        description = paramDto.getDescription();
    }

    public Category update(ChangeCategoryParamDto category) {
        name = category.getName();
        description = category.getDescription();

        return this;
    }
}
