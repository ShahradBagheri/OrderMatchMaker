package com.example.maktabproject.model.dto.rating;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record RatingRequestDto(@NotNull Long expertId,
                               @NotNull Float score,
                               String comment) {
}
