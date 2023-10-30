package com.example.maktabproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Builder
@Validated
public record OrderRequestDto(@NotNull Long subServiceId,
                              @NotNull Long customerId,
                              @NotNull Double suggestedPrice,
                              @NotBlank String details,
                              LocalDateTime startingDate,
                              @NotBlank String address) {
}
