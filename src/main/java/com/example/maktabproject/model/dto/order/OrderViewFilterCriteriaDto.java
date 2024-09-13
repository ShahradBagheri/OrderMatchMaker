package com.example.maktabproject.model.dto.order;


import com.example.maktabproject.model.enums.OrderState;

import java.time.LocalDateTime;

public record OrderViewFilterCriteriaDto(Long customerId,
                                         Long expertId,
                                         LocalDateTime startAfter,
                                         LocalDateTime startBefore,
                                         OrderState orderState,
                                         Long mainServiceId,
                                         Long subServiceId) {
}
