package com.microservice.microservicegym.exception;

public class UserAuthorizationException extends RuntimeException {
    public UserAuthorizationException(String message) {
        super(message);
    }
}
