package com.example.maktabproject.model.dto.offer;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record OfferRequestDto(@NotNull Long orderId,
                              @NotNull LocalDateTime startingDate,
                              @NotNull LocalDateTime completionDate,
                              @NotNull Double suggestedPrice) {
}
