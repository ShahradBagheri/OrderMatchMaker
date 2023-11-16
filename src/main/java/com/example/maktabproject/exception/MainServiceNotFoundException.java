package com.example.maktabproject.exception;

public class MainServiceNotFoundException extends RuntimeException {

    public MainServiceNotFoundException() {
    }

    public MainServiceNotFoundException(String message) {
        super(message);
    }
}
