package com.example.maktabproject.dto;

import com.example.maktabproject.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto userToDto(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }
}
