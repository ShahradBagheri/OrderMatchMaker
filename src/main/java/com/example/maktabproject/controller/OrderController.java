package com.example.maktabproject.controller;

import com.example.maktabproject.dto.OrderMapper;
import com.example.maktabproject.dto.OrderResponseDto;
import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.service.Impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/findForExpert")
    public ResponseEntity<List<OrderResponseDto>> findOrderForExpert(@RequestParam Long expertId) throws ExpertNotFoundException {

        List<Order> ordersForExpert = orderService.findOrdersForExpert(expertId);
        List<OrderResponseDto> orderListDto = ordersForExpert.stream().map(orderMapper::orderToDto).toList();
        return new ResponseEntity<>(orderListDto, HttpStatus.OK);
    }
}
