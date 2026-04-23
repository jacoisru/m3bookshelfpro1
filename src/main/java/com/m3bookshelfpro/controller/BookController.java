package com.m3bookshelfpro.controller;

import com.m3bookshelfpro.model.Book;
import com.m3bookshelfpro.service.BookService;
import com.m3bookshelfpro.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * MVC Controller handling all HTTP requests for Book operations.
 *
 * <p>Maps to the {@code /books} URL path and delegates all business
 * logic to {@link BookService} and {@link CategoryService}. Returns
 * Thymeleaf template names for view rendering. All methods include
 * SLF4J entry and exit logging.</p>
 *
 * @author Jacob Israel
 * @author Peyton Wolf
 * @version 4.0
 * @see BookService
 * @see CategoryService
 */
@Controller
@RequestMapping("/books")
public class BookController {

    /** SLF4J logger for this controller class. */
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    /** Service layer for book business logic. */
    @Autowired
    private BookService bookService;

    /** Service layer for category business logic, used when populating dropdowns. */
    @Autowired
    private CategoryService categoryService;

    /**
     * Displays a list of all books in the catalog.
     *
     * @param model the Spring MVC model used to pass data to the view
     * @return the Thymeleaf template name {@code books/list}
     */
    @GetMapping
    public String listBooks(Model model) {
        logger.info("ENTRY: listBooks()");
        model.addAttribute("books", bookService.getAllBooks());
        logger.info("EXIT: listBooks() - returned books/list");
        return "books/list";
    }

    /**
     * Displays the form for adding a new book.
     *
     * @param model the Spring MVC model used to pass data to the view
     * @return the Thymeleaf template name {@code books/add}
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("ENTRY: showAddForm()");
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        logger.info("EXIT: showAddForm() - returned books/add");
        return "books/add";
    }

    /**
     * Processes the form submission to add a new book.
     *
     * @param book the {@link Book} object populated from the form
     * @return redirect to {@code /books} after saving
     */
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        logger.info("ENTRY: addBook() - title={}", book.getTitle());
        bookService.saveBook(book);
        logger.info("EXIT: addBook() - book saved, redirecting to /books");
        return "redirect:/books";
    }

    /**
     * Displays the form for editing an existing book.
     *
     * @param id    the unique ID of the book to edit
     * @param model the Spring MVC model used to pass data to the view
     * @return the Thymeleaf template name {@code books/edit}
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("ENTRY: showEditForm() - id={}", id);
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        logger.info("EXIT: showEditForm() - returned books/edit");
        return "books/edit";
    }

    /**
     * Processes the form submission to update an existing book.
     *
     * @param id   the unique ID of the book being updated
     * @param book the {@link Book} object populated from the form
     * @return redirect to {@code /books} after saving
     */
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book) {
        logger.info("ENTRY: editBook() - id={}", id);
        book.setId(id);
        bookService.saveBook(book);
        logger.info("EXIT: editBook() - book updated, redirecting to /books");
        return "redirect:/books";
    }

    /**
     * Deletes a book from the catalog by its unique identifier.
     *
     * @param id the unique ID of the book to delete
     * @return redirect to {@code /books} after deletion
     */
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        logger.info("ENTRY: deleteBook() - id={}", id);
        bookService.deleteBookById(id);
        logger.info("EXIT: deleteBook() - book deleted, redirecting to /books");
        return "redirect:/books";
    }
}
