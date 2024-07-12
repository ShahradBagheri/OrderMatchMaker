package com.example.maktabproject.model.dto.user;

import com.example.maktabproject.model.enums.Role;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserFilterRequestDto(Role role,
                                   String firstname,
                                   String lastname,
                                   String email,
                                   LocalDateTime beforeCreationDate,
                                   LocalDateTime afterCreationDate,
                                   Long subServiceId,
                                   Float scoreHigher,
                                   Float scoreLower) {
}
