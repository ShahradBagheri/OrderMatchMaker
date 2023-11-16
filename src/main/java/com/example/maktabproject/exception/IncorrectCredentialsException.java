package com.example.maktabproject.exception;

public class IncorrectCredentialsException extends RuntimeException {

    public IncorrectCredentialsException() {
        super();
    }

    public IncorrectCredentialsException(String message) {
        super(message);
    }
}
