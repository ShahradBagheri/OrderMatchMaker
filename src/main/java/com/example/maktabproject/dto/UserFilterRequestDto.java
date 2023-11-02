package com.example.maktabproject.dto;

import com.example.maktabproject.model.enumeration.Role;

public record UserFilterRequestDto(Role role,
                                   String firstname,
                                   String lastname,
                                   String email,
                                   Long subServiceId,
                                   Float score) {
}
