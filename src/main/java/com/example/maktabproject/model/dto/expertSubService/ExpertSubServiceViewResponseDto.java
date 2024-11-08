package com.example.maktabproject.model.dto.expertSubService;

import lombok.Builder;

@Builder
public record ExpertSubServiceViewResponseDto(Long id,
                                              Long expertId,
                                              String expertFirstname,
                                              String expertLastname,
                                              String expertEmail,
                                              Long subServiceId,
                                              String subServiceName,
                                              Long subServiceBasePrice) {
}
