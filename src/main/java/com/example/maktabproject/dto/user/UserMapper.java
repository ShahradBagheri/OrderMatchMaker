package com.example.maktabproject.dto.user;

import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.User;
import com.example.maktabproject.service.Impl.SubServiceServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private final SubServiceServiceImpl subServiceService;

    public UserResponseDto userToDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

    public UserFilterCriteriaDto filterRequestToCriteriaDto(UserFilterRequestDto userFilterRequestDto) throws SubServiceNotFoundException {

        SubService subService = null;

        if (userFilterRequestDto.subServiceId() != null)
            subService = subServiceService.findById(userFilterRequestDto.subServiceId());

        return UserFilterCriteriaDto.builder()
                .role(userFilterRequestDto.role())
                .firstname(userFilterRequestDto.firstname())
                .lastname(userFilterRequestDto.lastname())
                .email(userFilterRequestDto.email())
                .subService(subService)
                .scoreHigher(userFilterRequestDto.scoreHigher())
                .scoreLower(userFilterRequestDto.scoreLower())
                .afterCreationDate(userFilterRequestDto.afterCreationDate())
                .beforeCreationDate(userFilterRequestDto.beforeCreationDate())
                .build();
    }
}
