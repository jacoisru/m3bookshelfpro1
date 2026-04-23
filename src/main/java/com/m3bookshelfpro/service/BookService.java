package com.m3bookshelfpro.service;

import com.m3bookshelfpro.model.Book;
import com.m3bookshelfpro.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class containing business logic for {@link Book} operations.
 *
 * <p>Acts as the middle layer between the controller and repository tiers
 * in the N-Layer MVC architecture. All methods include SLF4J entry and
 * exit logging for observability and debugging.</p>
 *
 * @author Jacob Israel
 * @author Peyton Wolf
 * @version 4.0
 * @see Book
 * @see BookRepository
 */
@Service
public class BookService {

    /** SLF4J logger for this service class. */
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    /** Repository used to perform database operations on Book entities. */
    @Autowired
    private BookRepository bookRepository;

    /**
     * Retrieves all books from the database.
     *
     * @return a {@link List} of all {@link Book} records
     */
    public List<Book> getAllBooks() {
        logger.info("ENTRY: getAllBooks()");
        List<Book> books = bookRepository.findAll();
        logger.info("EXIT: getAllBooks() - returned {} books", books.size());
        return books;
    }

    /**
     * Retrieves a single book by its unique identifier.
     *
     * @param id the unique ID of the book to retrieve
     * @return the {@link Book} if found, or {@code null} if not found
     */
    public Book getBookById(Long id) {
        logger.info("ENTRY: getBookById() - id={}", id);
        Book book = bookRepository.findById(id).orElse(null);
        logger.info("EXIT: getBookById() - found={}", book != null);
        return book;
    }

    /**
     * Saves a new book or updates an existing one in the database.
     *
     * @param book the {@link Book} object to persist
     */
    public void saveBook(Book book) {
        logger.info("ENTRY: saveBook() - title={}", book.getTitle());
        bookRepository.save(book);
        logger.info("EXIT: saveBook() - book persisted");
    }

    /**
     * Deletes a book from the database by its unique identifier.
     *
     * @param id the unique ID of the book to delete
     */
    public void deleteBookById(Long id) {
        logger.info("ENTRY: deleteBookById() - id={}", id);
        bookRepository.deleteById(id);
        logger.info("EXIT: deleteBookById() - book deleted");
    }
}
