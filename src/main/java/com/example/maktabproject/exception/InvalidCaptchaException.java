package com.example.maktabproject.exception;

public class InvalidCaptchaException extends Exception {

    public InvalidCaptchaException() {
    }

    public InvalidCaptchaException(String message) {
        super(message);
    }
}
