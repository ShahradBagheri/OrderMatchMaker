package com.example.maktabproject.model.dto.expert;

import com.example.maktabproject.model.enums.Role;
import lombok.Builder;

@Builder
public record ExpertResponseDto(Long id,
                                String firstname,
                                String lastname,
                                String username,
                                String email,
                                Role role) {
}
