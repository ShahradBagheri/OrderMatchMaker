package com.example.maktabproject.model.dto.subService;

import jakarta.validation.constraints.NotNull;

public record SubServiceEditRequestDto(@NotNull Long id,
                                       String name,
                                       Double basePrice,
                                       Long mainServiceId) {
}
