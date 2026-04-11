package com.m3bookshelfpro.service;

import com.m3bookshelfpro.model.Book;
import com.m3bookshelfpro.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public void saveBook(Book book) {
        bookRepository.save(book);
    }
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}