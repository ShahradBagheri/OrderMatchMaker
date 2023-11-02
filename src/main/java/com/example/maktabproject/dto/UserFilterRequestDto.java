package com.example.maktabproject.dto;

import com.example.maktabproject.model.enumeration.Role;
import lombok.Builder;

@Builder
public record UserFilterRequestDto(Role role,
                                   String firstname,
                                   String lastname,
                                   String email,
                                   Long subServiceId,
                                   Float scoreHigher,
                                   Float scoreLower) {
}
