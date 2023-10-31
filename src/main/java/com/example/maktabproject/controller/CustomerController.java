package com.example.maktabproject.controller;

import com.example.maktabproject.dto.*;
import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.exception.InvalidPriceException;
import com.example.maktabproject.exception.InvalidTimeException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.enumeration.OrderState;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import com.example.maktabproject.service.Impl.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final CustomerMapper customerMapper;
    private final OrderServiceImpl orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/changePassword")
    public ResponseEntity<CustomerResponseDto> changePassword(@RequestParam Long customerId,@RequestParam String newPassword) throws CustomerNotFoundException {

        return new ResponseEntity<>(customerMapper.customerToDto(customerService.changePassword(customerId,newPassword)), HttpStatus.OK);
    }

    @PostMapping("/order/submit")
    public ResponseEntity<OrderResponseDto> submitOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) throws CustomerNotFoundException, SubServiceNotFoundException, InvalidPriceException, InvalidTimeException {

        Order order = orderService.register(orderMapper.dtoToOrder(orderRequestDto));
        order.setOrderState(OrderState.WAITING_FOR_SUGGESTIONS);
        return new ResponseEntity<>(orderMapper.orderToDto(order),HttpStatus.OK);
    }
}
