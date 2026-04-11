package com.m3bookshelfpro.repository;

import com.m3bookshelfpro.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}