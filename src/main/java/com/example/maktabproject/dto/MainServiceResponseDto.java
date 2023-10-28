package com.example.maktabproject.dto;

import lombok.Builder;

@Builder
public record MainServiceResponseDto(Long id,
                                     String name) {
}
