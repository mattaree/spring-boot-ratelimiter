package com.atm.external.service.repository;

import com.atm.external.service.entities.BooksAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksAuthorRepository extends JpaRepository<BooksAuthor, Integer> {
}
