package com.example.maktabproject.dto.expert;

import com.example.maktabproject.model.enumeration.Role;
import lombok.Builder;

@Builder
public record ExpertResponseDto(Long id,
                                String firstname,
                                String lastname,
                                String username,
                                String email,
                                Role role) {
}
