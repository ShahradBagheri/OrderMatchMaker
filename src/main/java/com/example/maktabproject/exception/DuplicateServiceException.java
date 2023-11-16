package com.example.maktabproject.exception;

public class DuplicateServiceException extends RuntimeException {

    public DuplicateServiceException() {
        super();
    }

    public DuplicateServiceException(String message) {
        super(message);
    }
}
