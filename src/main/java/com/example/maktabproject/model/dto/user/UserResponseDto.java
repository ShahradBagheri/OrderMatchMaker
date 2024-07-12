package com.example.maktabproject.model.dto.user;

import lombok.Builder;

@Builder
public record UserResponseDto(Long id,
                              String firstname,
                              String lastname,
                              String username,
                              String email) {
}
