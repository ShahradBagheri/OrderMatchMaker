package com.example.maktabproject.model.dto.subService;

import lombok.Builder;

@Builder
public record SubServiceResponseDto(Long id,
                                    String name,
                                    Double basePrice,
                                    String comment) {
}
