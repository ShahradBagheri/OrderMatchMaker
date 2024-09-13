package com.example.maktabproject.model.dto.expert;

import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.Wallet;
import com.example.maktabproject.model.enums.Role;
import org.springframework.stereotype.Component;

import java.util.Base64;

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
                .imageBase64(expert.getImageData() != null ? encodeToBase64(expert.getImageData()): "null")
                .build();
    }

    public String encodeToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
