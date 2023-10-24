package com.example.maktabproject.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserRequestDto(String firstname,
                             String lastname,
                             @Email
                             String email,
                             @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$")
                             String password) {
}
