package com.example.maktabproject.dto;

import com.example.maktabproject.model.enumeration.OrderState;

import java.time.LocalDateTime;

public record OrderFilterRequestDto(Long customerId,
                                    Long expertId,
                                    LocalDateTime startAfter,
                                    LocalDateTime startBefore,
                                    OrderState orderState,
                                    Long mainServiceId,
                                    Long subServiceId) {
}
