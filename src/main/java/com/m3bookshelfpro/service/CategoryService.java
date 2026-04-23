package com.m3bookshelfpro.service;

import com.m3bookshelfpro.model.Category;
import com.m3bookshelfpro.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class containing business logic for {@link Category} operations.
 *
 * <p>Acts as the middle layer between the controller and repository tiers
 * in the N-Layer MVC architecture. All methods include SLF4J entry and
 * exit logging for observability and debugging.</p>
 *
 * @author Jacob Israel
 * @author Peyton Wolf
 * @version 4.0
 * @see Category
 * @see CategoryRepository
 */
@Service
public class CategoryService {

    /** SLF4J logger for this service class. */
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    /** Repository used to perform database operations on Category entities. */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retrieves all categories from the database.
     *
     * @return a {@link List} of all {@link Category} records
     */
    public List<Category> getAllCategories() {
        logger.info("ENTRY: getAllCategories()");
        List<Category> categories = categoryRepository.findAll();
        logger.info("EXIT: getAllCategories() - returned {} categories", categories.size());
        return categories;
    }

    /**
     * Retrieves a single category by its unique identifier.
     *
     * @param id the unique ID of the category to retrieve
     * @return the {@link Category} if found, or {@code null} if not found
     */
    public Category getCategoryById(Long id) {
        logger.info("ENTRY: getCategoryById() - id={}", id);
        Category category = categoryRepository.findById(id).orElse(null);
        logger.info("EXIT: getCategoryById() - found={}", category != null);
        return category;
    }

    /**
     * Saves a new category or updates an existing one in the database.
     *
     * @param category the {@link Category} object to persist
     */
    public void saveCategory(Category category) {
        logger.info("ENTRY: saveCategory() - name={}", category.getName());
        categoryRepository.save(category);
        logger.info("EXIT: saveCategory() - category persisted");
    }

    /**
     * Deletes a category from the database by its unique identifier.
     *
     * @param id the unique ID of the category to delete
     */
    public void deleteCategoryById(Long id) {
        logger.info("ENTRY: deleteCategoryById() - id={}", id);
        categoryRepository.deleteById(id);
        logger.info("EXIT: deleteCategoryById() - category deleted");
    }
}
