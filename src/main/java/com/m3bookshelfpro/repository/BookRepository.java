package com.m3bookshelfpro.repository;

import com.m3bookshelfpro.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
