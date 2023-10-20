package com.example.maktabproject.exception;

public class NotTheCorrectTimeToChangeStatusException extends Exception{

    public NotTheCorrectTimeToChangeStatusException() {
    }

    public NotTheCorrectTimeToChangeStatusException(String message) {
        super(message);
    }
}
