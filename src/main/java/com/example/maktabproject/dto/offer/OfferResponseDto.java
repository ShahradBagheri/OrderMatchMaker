package com.example.maktabproject.dto.offer;

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
