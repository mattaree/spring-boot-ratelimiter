package com.atm.feign.listener.service;

import com.atm.feign.listener.models.BooksAuthor;
import com.atm.feign.listener.models.BooksBook;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  This class shows use of RestTemplate REST client, however going forward RestTemplate will be deprecated in future versions.
 *  Hence moving forward it better to use WebClient.
 */
@Service
public class BooksAuthorRestTemplateService {

    private final Logger log = LoggerFactory.getLogger(BooksAuthorRestTemplateService.class);

    @Autowired
    private RestTemplate restTemplate;

    public List<BooksAuthor> getBooksAuthors() {
        String url = "http://localhost:8082/api/v1.0/fetch/booksAuthors";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        List<BooksAuthor> booksAuthors = new ArrayList<>();
        try {
            ResponseEntity <List<BooksAuthor>> responseEntity = restTemplate.exchange(url,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<BooksAuthor>>() {});
            if (responseEntity != null && responseEntity.hasBody()) {
                booksAuthors = responseEntity.getBody();
            }
        } catch (RestClientException rce) {
            rce.printStackTrace();
        }
        return booksAuthors;
    }

    @RateLimiter(name = "getBookById")
    public BooksBook getBookById(String bookId) {
        log.info("##### Inside getBookById method - invoking URL http://localhost:8082/api/v1.0/fetch for Book ID : {}", bookId);
        String url = "http://localhost:8082/api/v1.0/fetch"+"/"+bookId;
        return restTemplate.getForObject(url, BooksBook.class);
    }

    @RateLimiter(name = "getBookById", fallbackMethod = "getBookByIdFallbackMethod")
    public BooksBook getBookByIdWithFallback(String bookId) {
        log.info("Rate limit with fallback example");
        log.info("##### Inside getBookByIdWithFallback method - invoking URL http://localhost:8082/api/v1.0/fetch for Book ID : {}", bookId);
        String url = "http://localhost:8082/api/v1.0/fetch"+"/"+bookId;
        return restTemplate.getForObject(url, BooksBook.class);
    }

    private BooksBook getBookByIdFallbackMethod(String movieId, RequestNotPermitted requestNotPermitted) {
        Logger log = LoggerFactory.getLogger(BooksAuthorRestTemplateService.class);
        log.info("##### Inside getBookByIdFallbackMethod fallback method");
        log.info("##### EXCEPTION MESSAGE : {}", requestNotPermitted.getMessage());
        return new BooksBook(0, 0, 0, "Default", "N/A");
    }
}
