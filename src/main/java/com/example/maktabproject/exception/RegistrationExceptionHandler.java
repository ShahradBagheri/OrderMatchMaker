package com.example.maktabproject.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class RegistrationExceptionHandler {

    @ExceptionHandler(CustomExceptions.CustomerRegistrationException.class)
    public String handleCustomerRegistrationException(CustomExceptions.CustomerRegistrationException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("customerError", e.getMessage());
        return "redirect:../login";
    }

    @ExceptionHandler(CustomExceptions.ExpertRegistrationException.class)
    public String handleExpertRegistrationException(CustomExceptions.ExpertRegistrationException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("expertError", e.getMessage());
        return "redirect:../login";
    }
}
