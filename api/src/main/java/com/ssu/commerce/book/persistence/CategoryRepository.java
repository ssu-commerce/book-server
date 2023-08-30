package com.ssu.commerce.book.persistence;

import com.ssu.commerce.book.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
