package com.example.maktabproject.exception;

public class CustomExceptions {

    public static class CanNotSendTwoOffersException extends RuntimeException {
        public CanNotSendTwoOffersException(String message) {
            super(message);
        }
    }

    public static class CustomerNotFoundException extends RuntimeException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    public static class ExpertHasNoOfferForOfferException extends RuntimeException {
        public ExpertHasNoOfferForOfferException(String message) {
            super(message);
        }
    }

    public static class ExpertNotFoundException extends RuntimeException {
        public ExpertNotFoundException(String message) {
            super(message);
        }
    }

    public static class ImageToBigException extends RuntimeException {
        public ImageToBigException(String message) {
            super(message);
        }
    }

    public static class IncorrectCredentialsException extends RuntimeException {
        public IncorrectCredentialsException(String message) {
            super(message);
        }
    }

    public static class InsufficientFundException extends RuntimeException {
        public InsufficientFundException(String message) {
            super(message);
        }
    }

    public static class InvalidCaptchaException extends RuntimeException {
        public InvalidCaptchaException(String message) {
            super(message);
        }
    }

    public static class InvalidPriceException extends RuntimeException {
        public InvalidPriceException(String message) {
            super(message);
        }
    }

    public static class InvalidScoreException extends RuntimeException {
        public InvalidScoreException(String message) {
            super(message);
        }
    }

    public static class InvalidTimeException extends RuntimeException {
        public InvalidTimeException(String message) {
            super(message);
        }
    }

    public static class MainServiceNotFoundException extends RuntimeException {
        public MainServiceNotFoundException(String message) {
            super(message);
        }
    }

    public static class NotExpertsSubServiceException extends RuntimeException {
        public NotExpertsSubServiceException(String message) {
            super(message);
        }
    }

    public static class NotOrderOwnerException extends RuntimeException {
        public NotOrderOwnerException(String message) {
            super(message);
        }
    }

    public static class NotTheCorrectTimeToChangeStatusException extends RuntimeException {
        public NotTheCorrectTimeToChangeStatusException(String message) {
            super(message);
        }
    }

    public static class OfferNotFoundException extends RuntimeException {
        public OfferNotFoundException(String message) {
            super(message);
        }
    }

    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    public static class RatingNotFoundException extends RuntimeException {
        public RatingNotFoundException(String message) {
            super(message);
        }
    }

    public static class SubServiceNotFoundException extends RuntimeException {
        public SubServiceNotFoundException(String message) {
            super(message);
        }
    }

    public static class SubServiceTwoMainServiceException extends RuntimeException {
        public SubServiceTwoMainServiceException(String message) {
            super(message);
        }
    }

    public static class TokenNotFoundException extends RuntimeException {
        public TokenNotFoundException(String message) {
            super(message);
        }
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateServiceException extends RuntimeException{
        public DuplicateServiceException(String message) {
            super(message);
        }
    }

    public static class NoSuchServiceException extends RuntimeException{
        public NoSuchServiceException(String message) {
            super(message);
        }
    }

    public static class CustomerRegistrationException extends RuntimeException {
        public CustomerRegistrationException(String message) {
            super(message);
        }
    }

    public static class ExpertRegistrationException extends RuntimeException {
        public ExpertRegistrationException(String message) {
            super(message);
        }
    }
}
