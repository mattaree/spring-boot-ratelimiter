package com.atm.feign.listener.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

public class TooManyRequestException extends HttpClientErrorException {
    public TooManyRequestException(HttpStatusCode statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
