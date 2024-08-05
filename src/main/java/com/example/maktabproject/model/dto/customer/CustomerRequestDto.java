package com.example.maktabproject.model.dto.customer;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

@Validated
public record CustomerRequestDto(@NotBlank String firstname,
                                 @NotBlank String lastname,
                                 @NotBlank String username,
                                 @Email String email,
                                 @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$") String password) {
}
