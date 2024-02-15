package com.atm.external.service.repository;

import com.atm.external.service.entities.BooksBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksBookRepository extends JpaRepository<BooksBook, Integer> {
    BooksBook getBooksBookById(int bookId);
}
