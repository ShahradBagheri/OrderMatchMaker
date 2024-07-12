package com.example.maktabproject.model.dto.subService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

@Builder
@Validated
public record SubServiceRequestDto(@NotBlank String name,
                                   @NotNull Double basePrice,
                                   @NotBlank String Comment) {
}
