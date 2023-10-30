package com.example.maktabproject.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderResponseDto(Long id,
                               Long subServiceId,
                               Long customerId,
                               Long selectedOfferId,
                               Double suggestedPrice,
                               String details,
                               LocalDateTime startingDate,
                               String address) {
}
