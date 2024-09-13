package com.example.maktabproject.model.dto.user;

import com.example.maktabproject.model.enums.Role;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponseDto(Long id,
                              Role role,
                              String firstname,
                              String lastname,
                              String username,
                              String email,
                              LocalDate registerDate,
                              Float score,
                              String subService) {
}
