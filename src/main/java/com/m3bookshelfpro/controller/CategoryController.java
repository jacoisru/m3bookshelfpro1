package com.m3bookshelfpro.controller;

import com.m3bookshelfpro.model.Category;
import com.m3bookshelfpro.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        logger.info("ENTRY: listCategories()");
        model.addAttribute("categories", categoryService.getAllCategories());
        logger.info("EXIT: listCategories() - returned categories/list");
        return "categories/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("ENTRY: showAddForm()");
        model.addAttribute("category", new Category());
        logger.info("EXIT: showAddForm() - returned categories/add");
        return "categories/add";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category) {
        logger.info("ENTRY: addCategory() - name={}", category.getName());
        categoryService.saveCategory(category);
        logger.info("EXIT: addCategory() - category saved, redirecting to /categories");
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("ENTRY: showEditForm() - id={}", id);
        model.addAttribute("category", categoryService.getCategoryById(id));
        logger.info("EXIT: showEditForm() - returned categories/edit");
        return "categories/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, @ModelAttribute Category category) {
        logger.info("ENTRY: editCategory() - id={}", id);
        category.setId(id);
        categoryService.saveCategory(category);
        logger.info("EXIT: editCategory() - category updated, redirecting to /categories");
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        logger.info("ENTRY: deleteCategory() - id={}", id);
        categoryService.deleteCategoryById(id);
        logger.info("EXIT: deleteCategory() - category deleted, redirecting to /categories");
        return "redirect:/categories";
    }
}
