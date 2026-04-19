package com.m3bookshelfpro.service;

import com.m3bookshelfpro.model.Book;
import com.m3bookshelfpro.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        logger.info("ENTRY: getAllBooks()");
        List<Book> books = bookRepository.findAll();
        logger.info("EXIT: getAllBooks() - returned {} books", books.size());
        return books;
    }

    public Book getBookById(Long id) {
        logger.info("ENTRY: getBookById() - id={}", id);
        Book book = bookRepository.findById(id).orElse(null);
        logger.info("EXIT: getBookById() - found={}", book != null);
        return book;
    }

    public void saveBook(Book book) {
        logger.info("ENTRY: saveBook() - title={}", book.getTitle());
        bookRepository.save(book);
        logger.info("EXIT: saveBook() - book persisted");
    }

    public void deleteBookById(Long id) {
        logger.info("ENTRY: deleteBookById() - id={}", id);
        bookRepository.deleteById(id);
        logger.info("EXIT: deleteBookById() - book deleted");
    }
}
