package com.m3bookshelfpro.service;

import com.m3bookshelfpro.model.Category;
import com.m3bookshelfpro.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        logger.info("ENTRY: getAllCategories()");
        List<Category> categories = categoryRepository.findAll();
        logger.info("EXIT: getAllCategories() - returned {} categories", categories.size());
        return categories;
    }

    public Category getCategoryById(Long id) {
        logger.info("ENTRY: getCategoryById() - id={}", id);
        Category category = categoryRepository.findById(id).orElse(null);
        logger.info("EXIT: getCategoryById() - found={}", category != null);
        return category;
    }

    public void saveCategory(Category category) {
        logger.info("ENTRY: saveCategory() - name={}", category.getName());
        categoryRepository.save(category);
        logger.info("EXIT: saveCategory() - category persisted");
    }

    public void deleteCategoryById(Long id) {
        logger.info("ENTRY: deleteCategoryById() - id={}", id);
        categoryRepository.deleteById(id);
        logger.info("EXIT: deleteCategoryById() - category deleted");
    }
}
