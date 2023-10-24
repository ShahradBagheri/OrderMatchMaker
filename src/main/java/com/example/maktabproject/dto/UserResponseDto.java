package com.example.maktabproject.dto;

import com.example.maktabproject.model.enumeration.Role;
import lombok.Builder;

@Builder
public record UserResponseDto (Long id,
                               String firstname,
                               String lastname,
                               String email,
                               Role role){
}
