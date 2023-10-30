package com.example.maktabproject.dto;

import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import com.example.maktabproject.service.Impl.SubServiceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final SubServiceServiceImpl subServiceService;
    private final CustomerServiceImpl customerService;

    public Order dtoToOrder(OrderRequestDto orderRequestDto) throws SubServiceNotFoundException, CustomerNotFoundException {
        return Order.builder()
                .subService(subServiceService.findById(orderRequestDto.subServiceId()))
                .customer(customerService.findById(orderRequestDto.customerId()))
                .suggestedPrice(orderRequestDto.suggestedPrice())
                .startingDate(orderRequestDto.startingDate())
                .address(orderRequestDto.address())
                .details(orderRequestDto.details())
                .build();
    }

    public OrderResponseDto orderToDto(Order order){
        return OrderResponseDto.builder()
                .id(order.getId())
                .subServiceId(order.getSubService().getId())
                .customerId(order.getCustomer().getId())
                .selectedOfferId(order.getSelectedOffer().getId())
                .suggestedPrice(order.getSuggestedPrice())
                .startingDate(order.getStartingDate())
                .details(order.getDetails())
                .address(order.getAddress())
                .build();
    }
}
