package com.example.maktabproject.model.dto.customer;

import com.example.maktabproject.model.enums.Role;
import lombok.Builder;

@Builder
public record CustomerResponseDto(Long id,
                                  String firstname,
                                  String lastname,
                                  String email,
                                  String username,
                                  Role role) {
}
