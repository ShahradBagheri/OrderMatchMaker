package com.example.maktabproject.dto;

import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.exception.OrderNotFoundException;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import com.example.maktabproject.service.Impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferMapper {

    private final ExpertServiceImpl expertService;
    private final OrderServiceImpl orderService;

    public OfferResponseDto offerToDto(Offer offer) {
        return OfferResponseDto.builder()
                .id(offer.getId())
                .expertId(offer.getExpert().getId())
                .orderId(offer.getOrder().getId())
                .startingDate(offer.getStartingDate())
                .completionDate(offer.getCompletionDate())
                .suggestedPrice(offer.getSuggestedPrice())
                .build();
    }

    public Offer dtoToOffer(OfferRequestDto offerRequestDto) throws ExpertNotFoundException, OrderNotFoundException {
        return Offer.builder()
                .expert(expertService.findById(offerRequestDto.expertId()))
                .order(orderService.findById(offerRequestDto.orderId()))
                .startingDate(offerRequestDto.startingDate())
                .completionDate(offerRequestDto.completionDate())
                .suggestedPrice(offerRequestDto.suggestedPrice())
                .build();
    }
}
