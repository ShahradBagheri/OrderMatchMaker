package com.example.maktabproject.dto.user;

import lombok.Builder;

@Builder
public record UserResponseDto(Long id,
                              String firstname,
                              String lastname,
                              String username,
                              String email) {
}
