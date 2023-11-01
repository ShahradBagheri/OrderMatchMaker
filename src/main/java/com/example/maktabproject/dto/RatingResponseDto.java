package com.example.maktabproject.dto;

import lombok.Builder;

@Builder
public record RatingResponseDto(Long id,
                                Long expertId,
                                Long customerId,
                                Float score,
                                String comment) {
}
