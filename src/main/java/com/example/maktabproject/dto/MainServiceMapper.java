package com.example.maktabproject.dto;

import com.example.maktabproject.model.MainService;
import org.springframework.stereotype.Component;

@Component
public class MainServiceMapper {

    public MainServiceResponseDto mainServiceToDto(MainService mainService){
        return MainServiceResponseDto.builder()
                .id(mainService.getId())
                .name(mainService.getName())
                .build();
    }
}
