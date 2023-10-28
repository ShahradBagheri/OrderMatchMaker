package com.example.maktabproject.dto;

import com.example.maktabproject.model.MainService;

public class MainServiceMapper {

    public MainServiceResponseDto mainServiceToDto(MainService mainService){
        return MainServiceResponseDto.builder()
                .name(mainService.getName())
                .build();
    }
}
