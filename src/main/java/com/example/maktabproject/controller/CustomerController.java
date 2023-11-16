package com.example.maktabproject.controller;

import com.example.maktabproject.dto.*;
import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.Rating;
import com.example.maktabproject.model.enumeration.OrderState;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import com.example.maktabproject.service.Impl.OrderServiceImpl;
import com.example.maktabproject.service.Impl.RatingServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final CustomerMapper customerMapper;
    private final OrderServiceImpl orderService;
    private final OrderMapper orderMapper;
    private final RatingMapper ratingMapper;
    private final RatingServiceImpl ratingService;

    @PostMapping("/changePassword")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CustomerResponseDto> changePassword(@RequestParam String newPassword){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long customerId = customerService.findByUsername(username).getId();

        return new ResponseEntity<>(customerMapper.customerToDto(customerService.changePassword(customerId,newPassword)), HttpStatus.OK);
    }

    @PostMapping("/order/submit")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderResponseDto> submitOrder(@RequestBody @Valid OrderRequestDto orderRequestDto){

        Order order = orderMapper.dtoToOrder(orderRequestDto);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findByUsername(username);

        order.setOrderState(OrderState.WAITING_FOR_SUGGESTIONS);
        order.setCustomer(customer);
        order = orderService.register(order);
        return new ResponseEntity<>(orderMapper.orderToDto(order),HttpStatus.OK);
    }

    @PostMapping("/rating/submit")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<RatingResponseDto> submitRating(@RequestBody @Valid RatingRequestDto ratingRequestDto){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findByUsername(username);

        Rating rating = ratingMapper.dtoToRating(ratingRequestDto);
        rating.setCustomer(customer);
        return new ResponseEntity<>(ratingMapper.ratingToDto(ratingService.register(rating)),HttpStatus.OK);
    }

    @GetMapping("/filter/order")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<OrderResponseDto>> filterOrders(@RequestBody UserOrderFilterRequestDto userOrderFilterRequestDto){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findByUsername(username);

        return new ResponseEntity<>(orderService.filterOrderCustomer(customer.getId(),userOrderFilterRequestDto)
                .stream()
                .map(orderMapper::orderToDto)
                .toList()
                ,HttpStatus.OK);
    }
}
