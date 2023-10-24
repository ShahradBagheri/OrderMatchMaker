package com.example.maktabproject.dto;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.Wallet;
import com.example.maktabproject.model.enumeration.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public Customer userDtoToCustomer(UserRequestDto userRequestDto){

        User user = User.builder()
                .firstname(userRequestDto.firstname())
                .lastname(userRequestDto.lastname())
                .email(userRequestDto.email())
                .password(userRequestDto.password())
                .role(Role.CUSTOMER)
                .wallet(new Wallet())
                .build();

        return Customer.builder()
                .user(user)
                .build();
    }

    public Expert userDtoToExpert(UserRequestDto userRequestDto){

        User user = User.builder()
                .firstname(userRequestDto.firstname())
                .lastname(userRequestDto.lastname())
                .email(userRequestDto.email())
                .password(userRequestDto.password())
                .role(Role.EXPERT)
                .wallet(new Wallet())
                .build();

        return Expert.builder()
                .user(user)
                .build();
    }

    public UserResponseDto userToUserDto(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
