package com.example.maktabproject.model.dto.order;

import com.example.maktabproject.model.enums.OrderState;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderViewResponseDto(Long id,
                                   String orderDetails,
                                   String orderAddress,
                                   OrderState orderState,
                                   String subServiceName,
                                   String mainServiceName,
                                   Double subServiceBasePrice,
                                   LocalDateTime orderStartingDate,
                                   LocalDateTime offerCompletionDate,
                                   LocalDateTime offerStartingDate,
                                   Double offerSuggestedPrice,
                                   String customerUsername,
                                   String expertUsernames) {
}
