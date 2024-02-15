package com.atm.feign.listener.service;

import com.atm.feign.listener.feignclient.BooksAuthorFeignClient;
import com.atm.feign.listener.models.BooksAuthor;
import com.atm.feign.listener.models.BooksBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksAuthorFeignClientService {

    @Autowired
    private BooksAuthorFeignClient booksAuthorFeignClient;

    public List<BooksAuthor> getBooksAuthors() {
        return booksAuthorFeignClient.getBooksAuthors();
    }

    public BooksBook getBookById(String bookId) {
        return booksAuthorFeignClient.getBookById(bookId);
    }
}
