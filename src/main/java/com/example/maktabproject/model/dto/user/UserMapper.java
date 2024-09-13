package com.example.maktabproject.model.dto.user;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.enums.Role;
import com.example.maktabproject.service.ExpertService;
import com.example.maktabproject.service.Impl.SubServiceServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private final SubServiceServiceImpl subServiceService;
    private final ExpertService expertService;

    public UserResponseDto userToDto(User user) {
        Expert expert = new Expert();
        List<SubService> subService = new ArrayList<>();
        if(user.getRole() == Role.ROLE_EXPERT){
            expert = expertService.findByUser(user.getId());
            subService = subServiceService.findByExpertId(expert.getId());
        }
        return UserResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .username(user.getUsername())
                .registerDate(user.getRegistrationDate().toLocalDate())
                .role(user.getRole())
                .score(expert.getScore())
                .subService(subService.stream().map(SubService::getName).collect(Collectors.joining(", ")))
                .build();
    }

    public UserFilterCriteriaDto filterRequestToCriteriaDto(UserFilterRequestDto userFilterRequestDto) throws CustomExceptions.SubServiceNotFoundException {

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
