package com.example.maktabproject.model.dto.expertSubService;

import com.example.maktabproject.model.view.ExpertSubServiceView;
import org.springframework.stereotype.Component;

@Component
public class ExpertSubServiceViewMapper {

    public ExpertSubServiceViewResponseDto viewToDto(ExpertSubServiceView expertSubServiceView) {
        return ExpertSubServiceViewResponseDto.builder()
                .id(expertSubServiceView.getId())
                .expertId(expertSubServiceView.getExpertId())
                .expertFirstname(expertSubServiceView.getExpertFirstname())
                .expertLastname(expertSubServiceView.getExpertLastname())
                .expertEmail(expertSubServiceView.getExpertEmail())
                .subServiceId(expertSubServiceView.getSubServiceId())
                .subServiceName(expertSubServiceView.getSubServiceName())
                .subServiceBasePrice(expertSubServiceView.getSubServiceBasePrice())
                .build();
    }
}
