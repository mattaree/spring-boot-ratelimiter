package com.atm.feign.listener.exception;

import org.springframework.http.HttpStatusCode;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
    }

    public BookNotFoundException(String message) {
        super(message);
    }

}
