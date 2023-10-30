package com.example.maktabproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

public record ExpertRequestDto(@NotBlank String firstname,
                               @NotBlank String lastname,
                               @Email String email,
                               @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$") String password,
                               @NotNull MultipartFile image) {
}
