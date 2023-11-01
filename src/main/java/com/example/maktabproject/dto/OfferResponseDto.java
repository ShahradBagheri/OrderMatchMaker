package com.example.maktabproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OfferResponseDto(Long id,
                               Long expertId,
                               Long orderId,
                               LocalDateTime startingDate,
                               LocalDateTime completionDate,
                               Double suggestedPrice) {
}
