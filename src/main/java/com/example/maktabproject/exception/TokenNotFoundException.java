package com.example.maktabproject.exception;

public class TokenNotFoundException extends RuntimeException{

    public TokenNotFoundException() {
    }

    public TokenNotFoundException(String message) {
        super(message);
    }
}
