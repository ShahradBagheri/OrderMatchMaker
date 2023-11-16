package com.example.maktabproject.exception;

public class NotTheCorrectTimeToChangeStatusException extends RuntimeException {

    public NotTheCorrectTimeToChangeStatusException() {
    }

    public NotTheCorrectTimeToChangeStatusException(String message) {
        super(message);
    }
}
