package com.example.maktabproject.exception;

public class NoSuchServiceException extends RuntimeException {

    public NoSuchServiceException() {
        super();
    }

    public NoSuchServiceException(String message) {
        super(message);
    }
}
