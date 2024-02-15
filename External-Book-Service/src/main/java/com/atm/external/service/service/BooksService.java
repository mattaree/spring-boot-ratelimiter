package com.atm.external.service.service;

import com.atm.external.service.entities.BooksAuthor;
import com.atm.external.service.entities.BooksBook;
import com.atm.external.service.repository.BooksAuthorRepository;
import com.atm.external.service.repository.BooksBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    private BooksAuthorRepository booksAuthorRepository;
    @Autowired
    private BooksBookRepository booksBookRepository;

    public List<BooksAuthor> getBooksAuthors() {
        return booksAuthorRepository.findAll();
    }
    public BooksBook getBooksBookById(int bookId) {
        return booksBookRepository.getBooksBookById(bookId);
    }
}
