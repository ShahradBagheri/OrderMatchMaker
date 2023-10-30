package com.example.maktabproject.dto;

import com.example.maktabproject.model.SubService;
import org.springframework.stereotype.Component;

@Component
public class SubServiceMapper {

    public SubService subServiceDtoToSubService(SubServiceRequestDto subServiceRequestDto){
        return SubService.builder()
                .name(subServiceRequestDto.name())
                .basePrice(subServiceRequestDto.basePrice())
                .comment(subServiceRequestDto.Comment())
                .build();
    }

    public SubServiceResponseDto subServiceToDto(SubService subService){
        return SubServiceResponseDto.builder()
                .id(subService.getId())
                .name(subService.getName())
                .comment(subService.getComment())
                .basePrice(subService.getBasePrice())
                .build();
    }
}
