package com.example.maktabproject.model.dto.order;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderResponseDto(Long id,
                               Long subServiceId,
                               Long customerId,
                               Double suggestedPrice,
                               String details,
                               LocalDateTime startingDate,
                               String address) {
}
