package com.example.maktabproject.exception;

public class DuplicateServiceException extends Exception{

    public DuplicateServiceException(){
        super();
    }

    public DuplicateServiceException(String message){
        super(message);
    }
}
