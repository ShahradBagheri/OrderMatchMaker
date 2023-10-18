package com.example.maktabproject.exception;

public class OrderNotFoundException extends Exception{

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
