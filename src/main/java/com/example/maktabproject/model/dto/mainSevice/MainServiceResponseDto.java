package com.example.maktabproject.model.dto.mainSevice;

import lombok.Builder;

@Builder
public record MainServiceResponseDto(Long id,
                                     String name) {
}
