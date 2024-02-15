package com.atm.external.service.controller;

import com.atm.external.service.entities.BooksAuthor;
import com.atm.external.service.entities.BooksBook;
import com.atm.external.service.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/fetch")
public class BooksController {

    @Autowired
    private BooksService booksService;


    @GetMapping("/booksAuthors")
    public List<BooksAuthor> getBooksAuthors() {
        return booksService.getBooksAuthors();
    }

    @GetMapping("/{bookId}")
    public BooksBook getBookById(@PathVariable("bookId") int bookId) {
        return booksService.getBooksBookById(bookId);
    }
}
