package com.atm.feign.listener.feignclient;

import com.atm.feign.listener.config.AllRESTClientConfiguration;
import com.atm.feign.listener.models.BooksAuthor;
import com.atm.feign.listener.models.BooksBook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Feign client showcasing, how to invoke external microservice APIs (unsecured)
 * - value is the microservice name used by external microservice
 */

@FeignClient(value = "BOOKSAUTHOR-SERVICE", configuration = AllRESTClientConfiguration.class, url="${books.service.url}")
public interface BooksAuthorFeignClient {

    @GetMapping("/booksAuthors")
    public List<BooksAuthor> getBooksAuthors();

    @GetMapping("/{bookId}")
    public BooksBook getBookById(@PathVariable ("bookId") String bookId);
}
