package com.example.maktabproject.exception;

public class ExpertNotFoundException extends RuntimeException {

    public ExpertNotFoundException() {
    }

    public ExpertNotFoundException(String message) {
        super(message);
    }
}
