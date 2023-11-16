package com.example.maktabproject.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(Long id,
                              String firstname,
                              String lastname,
                              String username,
                              String email) {
}
