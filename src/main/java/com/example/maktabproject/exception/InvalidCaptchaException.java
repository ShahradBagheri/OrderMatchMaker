package com.example.maktabproject.exception;

public class InvalidCaptchaException extends RuntimeException {

    public InvalidCaptchaException() {
    }

    public InvalidCaptchaException(String message) {
        super(message);
    }
}
