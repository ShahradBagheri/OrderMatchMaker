package com.example.maktabproject.dto.mainSevice;

import lombok.Builder;

@Builder
public record MainServiceResponseDto(Long id,
                                     String name) {
}
