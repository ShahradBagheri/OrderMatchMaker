package com.example.maktabproject.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomExceptions.CustomerNotFoundException.class)
    public ResponseEntity<String> customerNotFoundExceptionHandler(CustomExceptions.CustomerNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.DuplicateServiceException.class)
    public ResponseEntity<String> duplicateServiceException(CustomExceptions.DuplicateServiceException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.ExpertHasNoOfferForOfferException.class)
    public ResponseEntity<String> expertHasNoOfferForOfferException(CustomExceptions.ExpertHasNoOfferForOfferException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.ExpertNotFoundException.class)
    public ResponseEntity<String> expertNotFoundException(CustomExceptions.ExpertNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.ImageToBigException.class)
    public ResponseEntity<String> imageToBigException(CustomExceptions.ImageToBigException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.IncorrectCredentialsException.class)
    public ResponseEntity<String> incorrectCredentialsException(CustomExceptions.IncorrectCredentialsException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.InsufficientFundException.class)
    public ResponseEntity<String> insufficientFundException(CustomExceptions.InsufficientFundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.InvalidCaptchaException.class)
    public ResponseEntity<String> invalidCaptchaException(CustomExceptions.InvalidCaptchaException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.InvalidPriceException.class)
    public ResponseEntity<String> invalidPriceException(CustomExceptions.InvalidPriceException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.InvalidScoreException.class)
    public ResponseEntity<String> invalidScoreException(CustomExceptions.InvalidScoreException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.InvalidTimeException.class)
    public ResponseEntity<String> invalidTimeException(CustomExceptions.InvalidTimeException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.MainServiceNotFoundException.class)
    public ResponseEntity<String> mainServiceNotFoundException(CustomExceptions.MainServiceNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.NoSuchServiceException.class)
    public ResponseEntity<String> noSuchServiceException(CustomExceptions.NoSuchServiceException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.NotTheCorrectTimeToChangeStatusException.class)
    public ResponseEntity<String> notTheCorrectTimeToChangeStatusException(CustomExceptions.NotTheCorrectTimeToChangeStatusException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.OfferNotFoundException.class)
    public ResponseEntity<String> offerNotFoundException(CustomExceptions.OfferNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.OrderNotFoundException.class)
    public ResponseEntity<String> orderNotFoundException(CustomExceptions.OrderNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.RatingNotFoundException.class)
    public ResponseEntity<String> ratingNotFoundException(CustomExceptions.RatingNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.SubServiceNotFoundException.class)
    public ResponseEntity<String> subServiceNotFoundException(CustomExceptions.SubServiceNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.SubServiceTwoMainServiceException.class)
    public ResponseEntity<String> subServiceTwoMainServiceException(CustomExceptions.SubServiceTwoMainServiceException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomExceptions.UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(CustomExceptions.UserNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerException(NullPointerException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> nullPointerException(ConstraintViolationException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> nullPointerException(DataAccessException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
