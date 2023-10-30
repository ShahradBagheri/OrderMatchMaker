package com.example.maktabproject.dto;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.Wallet;
import com.example.maktabproject.model.enumeration.Role;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer dtoToCustomer(CustomerRequestDto customerRequestDto){

        User user = User.builder()
                .firstname(customerRequestDto.firstname())
                .lastname(customerRequestDto.lastname())
                .email(customerRequestDto.email())
                .password(customerRequestDto.password())
                .role(Role.CUSTOMER)
                .wallet(new Wallet())
                .build();

        return Customer.builder()
                .user(user)
                .build();
    }

    public CustomerResponseDto customerToDto(User user){
        return CustomerResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
