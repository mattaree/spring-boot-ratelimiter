package com.atm.feign.listener.service;

import com.atm.feign.listener.exception.BooksException;
import com.atm.feign.listener.models.BooksAuthor;
import com.atm.feign.listener.models.BooksBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * WEBCLIENT - WebClient client
 * Spring WebClient is a non-blocking and reactive style for consuming external REST web services.
 * WebClient was added in Spring 5 (spring-webflux module) and provides functional-style API for
 * sending HTTP requests and handling the responses.
 * WebClient is immutable it is thread-safe. WebClient is meant to be used in a reactive environment,
 * where nothing is tied to a particular thread, but that doesn't mean you cannot use in traditional
 * Spring application.
 * 1. To use WebClient api, we add spring-boot-starter-webflux dependency to pom.xml.
 * 2. We can use WebCleint by adopting follow THREE given approaches,
 *     a) WebClient webClient = WebClient.create();  // With empty URI
 *     b) WebClient webClient = WebClient.create("https://client-domain.com");  // With specified root URI
 *     c) Using DefaultWebClientBuilder class, which uses builder pattern style fluent-API. It allows full customization.
 *        By creating a bean in the @Configuration class called webClient()
 *
 *  retrieve() or exchange() methods - retrieve() method performs HTTP request and retrieves response body.
 *                                     exchange() method returns ClientResponse with response status and headers.
 *                                     Response body can be fetched from ClientResponse instance.
 *
 */
@Service
public class BooksAuthorWebClientService {

    private String bookServiceUrl = "http://localhost:8082/api/v1.0/fetch";

//    @Value("${book.service.url}")
//    private String bookServiceUrl;

    private WebClient appWebClient;

    private WebClient basicWebClient;

    @Autowired
    public BooksAuthorWebClientService(@Qualifier("appWebClient") WebClient appWebClient,
                                       @Qualifier("basicWebClient") WebClient basicWebClient) {
        this.appWebClient = appWebClient;
        this.basicWebClient = basicWebClient;
    }

//    @EventListener
    public Flux<BooksAuthor> getBooksAuthors() {
        return appWebClient.get()
                .uri(bookServiceUrl+"/booksAuthors")
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(),
                          clientResponse -> handleErrorResponse(clientResponse.statusCode()))
                .bodyToFlux(BooksAuthor.class)
                .onErrorResume(Exception.class, e->Flux.empty()); // returning empty collection on error.
    }

    public Mono<BooksBook> getBookById(String bookId) {
        return basicWebClient.get()
                .uri("/{id}", bookId)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(BooksBook.class);
                    } else {
                        return response.createException().flatMap(Mono::error); // Turn to error
                    }
                });
    }

    private Mono<? extends Throwable> handleErrorResponse(HttpStatusCode statusCode) {
        // Handle non-success status codes here (e.g., logging or custom error handling)
        return Mono.error(new BooksException("Failed to fetch data from Book Author Service. Status code: " + statusCode));
    }
}
