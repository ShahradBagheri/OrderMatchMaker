package com.example.maktabproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Controller
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<String> incorrectCredentialsExceptionHandler(IncorrectCredentialsException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> customerNotFoundExceptionHandler(CustomerNotFoundException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(DuplicateServiceException.class)
    public ResponseEntity<String> duplicateServiceException(DuplicateServiceException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ExpertHasNoOfferForOfferException.class)
    public ResponseEntity<String> expertHasNoOfferForOfferException(ExpertHasNoOfferForOfferException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ExpertNotFoundException.class)
    public ResponseEntity<String> expertNotFoundException(ExpertNotFoundException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ImageToBigException.class)
    public ResponseEntity<String> imageToBigException(ImageToBigException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<String> incorrectCredentialsException(IncorrectCredentialsException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InsufficientFundException.class)
    public ResponseEntity<String> insufficientFundException(InsufficientFundException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidCaptchaException.class)
    public ResponseEntity<String> invalidCaptchaException(InvalidCaptchaException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<String> invalidPriceException(InvalidPriceException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidScoreException.class)
    public ResponseEntity<String> invalidScoreException(InvalidScoreException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidTimeException.class)
    public ResponseEntity<String> invalidTimeException(InvalidTimeException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MainServiceNotFoundException.class)
    public ResponseEntity<String> mainServiceNotFoundException(MainServiceNotFoundException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(NoSuchServiceException.class)
    public ResponseEntity<String> noSuchServiceException(NoSuchServiceException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(NotTheCorrectTimeToChangeStatusException.class)
    public ResponseEntity<String> notTheCorrectTimeToChangeStatusException(NotTheCorrectTimeToChangeStatusException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<String> offerNotFoundException(OfferNotFoundException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> orderNotFoundException(OrderNotFoundException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<String> ratingNotFoundException(RatingNotFoundException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(SubServiceNotFoundException.class)
    public ResponseEntity<String> subServiceNotFoundException(SubServiceNotFoundException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(SubServiceTwoMainServiceException.class)
    public ResponseEntity<String> subServiceTwoMainServiceException(SubServiceTwoMainServiceException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
