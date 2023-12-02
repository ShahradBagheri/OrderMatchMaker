package com.example.maktabproject.dto.user;

import com.example.maktabproject.model.enumeration.Role;
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
