package com.example.maktabproject.controller;

import com.example.maktabproject.dto.OrderMapper;
import com.example.maktabproject.dto.OrderResponseDto;
import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.service.Impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/selectOffer")
    public void selectOffer(@RequestParam Long offerId,@RequestParam Long orderId) throws OrderNotFoundException, InvalidPriceException, InvalidTimeException, ExpertHasNoOfferForOfferException, OfferNotFoundException {

        orderService.choseOffer(offerId,orderId);
    }

    @PostMapping("/changeState/started")
    public ResponseEntity<OrderResponseDto> changeStateStarted(@RequestParam Long orderId) throws OrderNotFoundException, InvalidPriceException, InvalidTimeException, NotTheCorrectTimeToChangeStatusException {

        return new ResponseEntity<>(orderMapper.orderToDto(orderService.statusToStarted(orderId)),HttpStatus.OK);
    }

    @PostMapping("/changeState/finished")
    public ResponseEntity<OrderResponseDto> changeStateFinished(@RequestParam Long orderId) throws OrderNotFoundException, InvalidPriceException, InvalidTimeException, NotTheCorrectTimeToChangeStatusException {

        return new ResponseEntity<>(orderMapper.orderToDto(orderService.statusToFinished(orderId)),HttpStatus.OK);
    }
}
