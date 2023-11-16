package com.example.maktabproject.exception;

public class InvalidScoreException extends RuntimeException {

    public InvalidScoreException() {
    }

    public InvalidScoreException(String message) {
        super(message);
    }
}
