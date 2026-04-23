package com.m3bookshelfpro.controller;

import com.m3bookshelfpro.model.Category;
import com.m3bookshelfpro.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * MVC Controller handling all HTTP requests for Category operations.
 *
 * <p>Maps to the {@code /categories} URL path and delegates all business
 * logic to {@link CategoryService}. Returns Thymeleaf template names
 * for view rendering. All methods include SLF4J entry and exit logging.</p>
 *
 * @author Jacob Israel
 * @author Peyton Wolf
 * @version 4.0
 * @see CategoryService
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    /** SLF4J logger for this controller class. */
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    /** Service layer for category business logic. */
    @Autowired
    private CategoryService categoryService;

    /**
     * Displays a list of all categories.
     *
     * @param model the Spring MVC model used to pass data to the view
     * @return the Thymeleaf template name {@code categories/list}
     */
    @GetMapping
    public String listCategories(Model model) {
        logger.info("ENTRY: listCategories()");
        model.addAttribute("categories", categoryService.getAllCategories());
        logger.info("EXIT: listCategories() - returned categories/list");
        return "categories/list";
    }

    /**
     * Displays the form for adding a new category.
     *
     * @param model the Spring MVC model used to pass data to the view
     * @return the Thymeleaf template name {@code categories/add}
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("ENTRY: showAddForm()");
        model.addAttribute("category", new Category());
        logger.info("EXIT: showAddForm() - returned categories/add");
        return "categories/add";
    }

    /**
     * Processes the form submission to add a new category.
     *
     * @param category the {@link Category} object populated from the form
     * @return redirect to {@code /categories} after saving
     */
    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category) {
        logger.info("ENTRY: addCategory() - name={}", category.getName());
        categoryService.saveCategory(category);
        logger.info("EXIT: addCategory() - category saved, redirecting to /categories");
        return "redirect:/categories";
    }

    /**
     * Displays the form for editing an existing category.
     *
     * @param id    the unique ID of the category to edit
     * @param model the Spring MVC model used to pass data to the view
     * @return the Thymeleaf template name {@code categories/edit}
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("ENTRY: showEditForm() - id={}", id);
        model.addAttribute("category", categoryService.getCategoryById(id));
        logger.info("EXIT: showEditForm() - returned categories/edit");
        return "categories/edit";
    }

    /**
     * Processes the form submission to update an existing category.
     *
     * @param id       the unique ID of the category being updated
     * @param category the {@link Category} object populated from the form
     * @return redirect to {@code /categories} after saving
     */
    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, @ModelAttribute Category category) {
        logger.info("ENTRY: editCategory() - id={}", id);
        category.setId(id);
        categoryService.saveCategory(category);
        logger.info("EXIT: editCategory() - category updated, redirecting to /categories");
        return "redirect:/categories";
    }

    /**
     * Deletes a category by its unique identifier.
     *
     * @param id the unique ID of the category to delete
     * @return redirect to {@code /categories} after deletion
     */
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        logger.info("ENTRY: deleteCategory() - id={}", id);
        categoryService.deleteCategoryById(id);
        logger.info("EXIT: deleteCategory() - category deleted, redirecting to /categories");
        return "redirect:/categories";
    }
}
