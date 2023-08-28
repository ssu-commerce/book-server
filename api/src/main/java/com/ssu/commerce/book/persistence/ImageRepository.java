package com.ssu.commerce.book.persistence;

import com.ssu.commerce.book.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
