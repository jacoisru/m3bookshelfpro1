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

@Controller
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listBooks(Model model) {
        logger.info("ENTRY: listBooks()");
        model.addAttribute("books", bookService.getAllBooks());
        logger.info("EXIT: listBooks() - returned books/list");
        return "books/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("ENTRY: showAddForm()");
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        logger.info("EXIT: showAddForm() - returned books/add");
        return "books/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        logger.info("ENTRY: addBook() - title={}", book.getTitle());
        bookService.saveBook(book);
        logger.info("EXIT: addBook() - book saved, redirecting to /books");
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("ENTRY: showEditForm() - id={}", id);
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        logger.info("EXIT: showEditForm() - returned books/edit");
        return "books/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book) {
        logger.info("ENTRY: editBook() - id={}", id);
        book.setId(id);
        bookService.saveBook(book);
        logger.info("EXIT: editBook() - book updated, redirecting to /books");
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        logger.info("ENTRY: deleteBook() - id={}", id);
        bookService.deleteBookById(id);
        logger.info("EXIT: deleteBook() - book deleted, redirecting to /books");
        return "redirect:/books";
    }
}
