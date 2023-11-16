package com.example.maktabproject.exception;

public class CanNotSendTwoOffersException extends RuntimeException {

    public CanNotSendTwoOffersException() {
    }

    public CanNotSendTwoOffersException(String message) {
        super(message);
    }
}
