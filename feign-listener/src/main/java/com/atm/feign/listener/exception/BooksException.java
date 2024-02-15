package com.atm.feign.listener.exception;

public class BooksException extends Exception {
    public BooksException() {
    }

    public BooksException(String message) {
        super(message);
    }
}
