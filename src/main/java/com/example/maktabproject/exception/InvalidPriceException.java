package com.example.maktabproject.exception;

public class InvalidPriceException extends Exception{

    public InvalidPriceException() {
    }

    public InvalidPriceException(String message) {
        super(message);
    }
}
