package com.example.maktabproject.dto;

import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.Wallet;
import com.example.maktabproject.model.enumeration.Role;
import org.springframework.stereotype.Component;

@Component
public class ExpertMapper {

    public Expert dtoToExpert(ExpertRequestDto expertRequestDto) {

        User user = User.builder()
                .firstname(expertRequestDto.firstname())
                .lastname(expertRequestDto.lastname())
                .email(expertRequestDto.email())
                .password(expertRequestDto.password())
                .role(Role.ROLE_EXPERT)
                .wallet(new Wallet())
                .username(expertRequestDto.username())
                .build();

        return Expert.builder()
                .user(user)
                .build();
    }

    public ExpertResponseDto expertToDto(Expert expert) {
        return ExpertResponseDto.builder()
                .id(expert.getId())
                .firstname(expert.getUser().getFirstname())
                .lastname(expert.getUser().getLastname())
                .email(expert.getUser().getEmail())
                .role(expert.getUser().getRole())
                .username(expert.getUser().getUsername())
                .build();
    }
}
