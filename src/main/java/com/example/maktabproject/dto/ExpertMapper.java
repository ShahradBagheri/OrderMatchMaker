package com.example.maktabproject.dto;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.Wallet;
import com.example.maktabproject.model.enumeration.Role;

public class ExpertMapper {

    public Expert dtoToExpert(ExpertRequestDto expertRequestDto){

        User user = User.builder()
                .firstname(expertRequestDto.firstname())
                .lastname(expertRequestDto.lastname())
                .email(expertRequestDto.email())
                .password(expertRequestDto.password())
                .role(Role.EXPERT)
                .wallet(new Wallet())
                .build();

        return Expert.builder()
                .user(user)
                .build();
    }

    public ExpertResponseDto expertToDto(User user){
        return ExpertResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
