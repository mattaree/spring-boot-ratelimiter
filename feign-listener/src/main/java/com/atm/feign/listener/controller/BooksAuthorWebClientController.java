package com.atm.feign.listener.controller;

import com.atm.feign.listener.models.BooksAuthor;
import com.atm.feign.listener.models.BooksBook;
import com.atm.feign.listener.service.BooksAuthorWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Both GET methods in this controller calls WebClient
 */
@RestController
@RequestMapping("/wc/external")
public class BooksAuthorWebClientController {

    @Autowired
    private BooksAuthorWebClientService booksAuthorWebClientService;

    @GetMapping("/booksAuthors")
    public Flux<BooksAuthor> getBooksAuthors() {
        return booksAuthorWebClientService.getBooksAuthors();
    }

    @GetMapping("/{bookId}")
    public Mono<BooksBook> getBookById(@PathVariable("bookId") String bookId) {
        return booksAuthorWebClientService.getBookById(bookId);
    }
}
