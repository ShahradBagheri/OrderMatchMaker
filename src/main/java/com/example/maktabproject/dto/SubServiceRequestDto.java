package com.example.maktabproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SubServiceRequestDto(@NotBlank String name,
                                   @NotNull Double basePrice,
                                   @NotBlank String Comment) {
}
