package com.example.maktabproject.model.dto.user;

import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.enums.Role;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserFilterCriteriaDto(Role role,
                                    String firstname,
                                    String lastname,
                                    String email,
                                    LocalDateTime beforeCreationDate,
                                    LocalDateTime afterCreationDate,
                                    SubService subService,
                                    Float scoreHigher,
                                    Float scoreLower) {
}
