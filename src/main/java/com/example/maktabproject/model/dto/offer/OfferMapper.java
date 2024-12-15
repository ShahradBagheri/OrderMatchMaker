package com.example.maktabproject.model.dto.offer;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.model.view.OfferView;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import com.example.maktabproject.service.Impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferMapper {

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

    public OfferViewResponseDto offerViewToDto(OfferView offerView){
        return OfferViewResponseDto.builder()
                .id(offerView.getId())
                .offerStartingDate(offerView.getOfferStartingDate())
                .offerCompletionDate(offerView.getOfferCompletionDate())
                .offerSuggestedPrice(offerView.getOfferSuggestedPrice())
                .orderDetails(offerView.getOrderDetails())
                .orderAddress(offerView.getOrderAddress())
                .subServiceName(offerView.getSubServiceName())
                .subServiceBasePrice(offerView.getSubServiceBasePrice())
                .customerUsername(offerView.getCustomerUsername())
                .expertUsername(offerView.getExpertUsername())
                .build();
    }

    public Offer dtoToOffer(OfferRequestDto offerRequestDto) throws CustomExceptions.ExpertNotFoundException, CustomExceptions.OrderNotFoundException {
        return Offer.builder()
                .order(orderService.findById(offerRequestDto.orderId()))
                .startingDate(offerRequestDto.startingDate())
                .completionDate(offerRequestDto.completionDate())
                .suggestedPrice(offerRequestDto.suggestedPrice())
                .build();
    }
}
