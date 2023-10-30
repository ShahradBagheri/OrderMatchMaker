package com.example.maktabproject.dto;

import lombok.Builder;

@Builder
public record SubServiceResponseDto(Long id,
                                    String name,
                                    Double basePrice,
                                    String comment) {
}
