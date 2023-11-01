package com.example.maktabproject.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record RatingRequestDto(@NotNull Long expertId,
                               @NotNull Long customerId,
                               @NotNull Float score,
                               String comment) {
}
