package com.example.maktabproject.model.dto.subService;

import com.example.maktabproject.model.SubService;
import com.example.maktabproject.service.Impl.MainServiceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubServiceMapper {

    private final MainServiceServiceImpl mainServiceService;

    public SubService subServiceDtoToSubService(SubServiceRequestDto subServiceRequestDto) {
        return SubService.builder()
                .name(subServiceRequestDto.name())
                .basePrice(subServiceRequestDto.basePrice())
                .comment(subServiceRequestDto.Comment())
                .mainService(mainServiceService.findById(subServiceRequestDto.mainServiceId()))
                .build();
    }

    public SubServiceResponseDto subServiceToDto(SubService subService) {
        return SubServiceResponseDto.builder()
                .id(subService.getId())
                .name(subService.getName())
                .comment(subService.getComment())
                .basePrice(subService.getBasePrice())
                .build();
    }
}
