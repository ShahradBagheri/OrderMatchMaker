package com.example.maktabproject.dto;

import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.enumeration.Role;
import lombok.Builder;

@Builder
public record UserFilterCriteriaDto(Role role,
                                    String firstname,
                                    String lastname,
                                    String email,
                                    SubService subService,
                                    Float score) {
}
