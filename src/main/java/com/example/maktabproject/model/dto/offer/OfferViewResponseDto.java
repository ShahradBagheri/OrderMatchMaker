package com.example.maktabproject.model.dto.offer;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OfferViewResponseDto(Long id,
                                   LocalDateTime offerCompletionDate,
                                   LocalDateTime offerStartingDate,
                                   Double offerSuggestedPrice,
                                   String orderDetails,
                                   String orderAddress,
                                   String subServiceName,
                                   Double subServiceBasePrice,
                                   String customerUsername,
                                   String expertUsername) {
}
