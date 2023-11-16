package com.example.maktabproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Validated
public record ExpertRequestDto(@NotBlank String firstname,
                               @NotBlank String lastname,
                               @Email String email,
                               @NotBlank String username,
                               @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$") String password,
                               @NotNull MultipartFile image) {
}
