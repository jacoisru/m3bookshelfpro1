package com.m3bookshelfpro.repository;

import com.m3bookshelfpro.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Book} entity persistence operations.
 *
 * <p>Extends Spring Data JPA's {@link JpaRepository} to inherit standard
 * CRUD operations including {@code findAll()}, {@code findById()},
 * {@code save()}, and {@code deleteById()} without requiring any
 * explicit implementation.</p>
 *
 * <p>Spring Data JPA automatically provides the implementation at runtime
 * through proxy generation.</p>
 *
 * @author Jacob Israel
 * @author Peyton Wolf
 * @version 4.0
 * @see Book
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
