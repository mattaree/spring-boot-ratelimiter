package com.atm.feign.listener.controller;

import com.atm.feign.listener.models.BooksAuthor;
import com.atm.feign.listener.models.BooksBook;
import com.atm.feign.listener.service.BooksAuthorFeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Both GET methods in this controller calls methods of Feign client
 */
@RestController
@RequestMapping("/fc/external")
public class BooksAuthorFeignClientController {
    @Autowired
    private BooksAuthorFeignClientService booksAuthorService;

    @GetMapping("/booksAuthors")
    public List<BooksAuthor> getBooksAuthors() {
        return booksAuthorService.getBooksAuthors();
    }

    @GetMapping("/{bookId}")
    public BooksBook getBookById(@PathVariable("bookId") String bookId) {
        return booksAuthorService.getBookById(bookId);
    }
}
