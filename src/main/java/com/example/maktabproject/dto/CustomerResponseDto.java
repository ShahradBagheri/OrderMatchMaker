package com.example.maktabproject.dto;

import com.example.maktabproject.model.enumeration.Role;
import lombok.Builder;

@Builder
public record CustomerResponseDto(Long id,
                                  String firstname,
                                  String lastname,
                                  String email,
                                  Role role){
}
