package com.atm.feign.listener.controller;

import com.atm.feign.listener.models.BooksAuthor;
import com.atm.feign.listener.models.BooksBook;
import com.atm.feign.listener.service.BooksAuthorRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Both GET methods in this controller calls RestTemplate
 */
@RestController
@RequestMapping("/rt/external")
public class BooksAuthorRestTemplateController {

    @Autowired
    private BooksAuthorRestTemplateService booksAuthorRestTemplateService;

    @GetMapping("/booksAuthors")
    public List<BooksAuthor> getBooksAuthors() {
        return booksAuthorRestTemplateService.getBooksAuthors();
    }

    @GetMapping("/{bookId}")
    public BooksBook getBookById(@PathVariable("bookId") String bookId) {
        return booksAuthorRestTemplateService.getBookById(bookId);
    }

    @GetMapping("/fallback/{bookId}")
    public BooksBook getBookByIdWithFallback(@PathVariable("bookId") String bookId) {
        return booksAuthorRestTemplateService.getBookByIdWithFallback(bookId);
    }
}
