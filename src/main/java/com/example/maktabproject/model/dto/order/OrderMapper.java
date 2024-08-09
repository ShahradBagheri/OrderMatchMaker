package com.example.maktabproject.model.dto.order;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.*;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import com.example.maktabproject.service.Impl.MainServiceServiceImpl;
import com.example.maktabproject.service.Impl.SubServiceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final SubServiceServiceImpl subServiceService;
    private final CustomerServiceImpl customerService;
    private final ExpertServiceImpl expertService;
    private final MainServiceServiceImpl mainServiceService;

    public Order dtoToOrder(OrderRequestDto orderRequestDto) throws CustomExceptions.SubServiceNotFoundException, CustomExceptions.CustomerNotFoundException {
        return Order.builder()
                .subService(subServiceService.findById(orderRequestDto.subServiceId()))
                .suggestedPrice(orderRequestDto.suggestedPrice())
                .startingDate(orderRequestDto.startingDate())
                .address(orderRequestDto.address())
                .details(orderRequestDto.details())
                .build();
    }

    public OrderResponseDto orderToDto(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .subServiceId(order.getSubService().getId())
                .customerId(order.getCustomer().getId())
                .suggestedPrice(order.getSuggestedPrice())
                .startingDate(order.getStartingDate())
                .details(order.getDetails())
                .address(order.getAddress())
                .build();
    }

    public OrderFilterCriteriaDto dtoToCriteria(OrderFilterRequestDto orderFilterRequestDto) {

        Expert expert = null;
        Customer customer = null;
        SubService subService = null;
        MainService mainService = null;

        if (orderFilterRequestDto.customerId() != null)
            customer = customerService.findById(orderFilterRequestDto.customerId());
        if (orderFilterRequestDto.expertId() != null)
            expert = expertService.findById(orderFilterRequestDto.expertId());
        if (orderFilterRequestDto.subServiceId() != null)
            subService = subServiceService.findById(orderFilterRequestDto.subServiceId());
        if (orderFilterRequestDto.mainServiceId() != null)
            mainService = mainServiceService.findById(orderFilterRequestDto.mainServiceId());

        return OrderFilterCriteriaDto.builder()
                .customer(customer)
                .expert(expert)
                .startAfter(orderFilterRequestDto.startAfter())
                .startBefore(orderFilterRequestDto.startBefore())
                .orderState(orderFilterRequestDto.orderState())
                .subService(subService)
                .mainService(mainService)
                .build();
    }
}
