package com.atm.feign.listener.config;

import com.atm.feign.listener.logger.FeignClientLogger;
import feign.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * There are four logging levels to choose from, for FEIGN Logger Level
 *     NONE – no logging, which is by default
 *     BASIC – logs request method, URL and response status
 *     HEADERS – logs basic information with request and response headers
 *     FULL – logs body, headers and metadata for both request and response
 */


@Configuration
public class AllRESTClientConfiguration {

//    @Value("${book.service.url}")
//    private String bookServiceUrl;

    private String bookServiceUrl = "http://localhost:8082/api/v1.0/fetch";

    /**
     * Setting Logging level for Feign Client
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * Registering custom logger for Feign client
     */
    @Bean
    public Logger logger() {
        return new FeignClientLogger();
    }

    /**
     * RestTemplate bean
     */
    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * WebClient bean for complete application
     * For showcasing use of @Qualifier two beans of WebClient are created
     * one is to be used for entire application and other one is a basic one.
     */
    @Bean
    public WebClient appWebClient() {
        return WebClient.builder()
                .baseUrl(bookServiceUrl)
                .defaultCookie("cookie-name", "cookie-value")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    /**
     * Basic WebClient bean
     */
    @Bean
    public WebClient basicWebClient() {
        return WebClient.builder()
                .baseUrl(bookServiceUrl)
                .build();
    }
}
