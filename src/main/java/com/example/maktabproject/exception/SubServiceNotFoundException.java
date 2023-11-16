package com.example.maktabproject.exception;

public class SubServiceNotFoundException extends RuntimeException {

    public SubServiceNotFoundException() {
    }

    public SubServiceNotFoundException(String message) {
        super(message);
    }
}
