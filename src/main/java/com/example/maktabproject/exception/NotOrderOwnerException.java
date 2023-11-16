package com.example.maktabproject.exception;

public class NotOrderOwnerException extends RuntimeException{

    public NotOrderOwnerException() {
    }

    public NotOrderOwnerException(String message) {
        super(message);
    }
}
