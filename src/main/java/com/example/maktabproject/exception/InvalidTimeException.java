package com.example.maktabproject.exception;

public class InvalidTimeException extends RuntimeException {

    public InvalidTimeException() {
    }

    public InvalidTimeException(String message) {
        super(message);
    }
}
