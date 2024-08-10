package com.example.maktabproject.model.dto.subService;

import lombok.Builder;

@Builder
public record SubServiceViewDToResponseDto(Long id,
                                           String name,
                                           Double basePrice,
                                           String comment,
                                           String mainServiceName) {
}
