package com.example.maktabproject.dto;

import com.example.maktabproject.model.enumeration.OrderState;

import java.time.LocalDateTime;

public record OrderFilterRequestDto(Long customerId,
                                    Long expertId,
                                    LocalDateTime doneAfter,
                                    LocalDateTime doneBefore,
                                    OrderState orderState,
                                    Long mainServiceId,
                                    Long subServiceId) {
}
