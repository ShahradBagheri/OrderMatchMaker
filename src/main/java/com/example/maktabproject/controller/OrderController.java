package com.example.maktabproject.controller;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.dto.order.OrderMapper;
import com.example.maktabproject.model.dto.order.OrderResponseDto;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.enums.ExpertStatus;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import com.example.maktabproject.service.Impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;
    private final OrderMapper orderMapper;
    private final ExpertServiceImpl expertService;
    private final CustomerServiceImpl customerService;

    @GetMapping("/findForExpert")
    @PreAuthorize("hasRole('EXPERT')")
    public ResponseEntity<List<OrderResponseDto>> findOrderForExpert() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Expert expert = expertService.findByUsername(username);

        if(expert.getExpertStatus() != ExpertStatus.APPROVED)
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

        List<Order> ordersForExpert = orderService.findOrdersForExpert(expert.getId());
        List<OrderResponseDto> orderListDto = ordersForExpert.stream().map(orderMapper::orderToDto).toList();
        return new ResponseEntity<>(orderListDto, HttpStatus.OK);
    }

    @PostMapping("/selectOffer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void selectOffer(@RequestParam Long offerId, @RequestParam Long orderId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findByUsername(username);

        if (!Objects.equals(orderService.findById(orderId).getCustomer().getId(), customer.getId()))
            throw new CustomExceptions.NotOrderOwnerException("you dont own this order!");

        orderService.choseOffer(offerId, orderId);
    }

    @PostMapping("/changeState/started")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderResponseDto> changeStateStarted(@RequestParam Long orderId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findByUsername(username);

        if (!Objects.equals(orderService.findById(orderId).getCustomer().getId(), customer.getId()))
            throw new CustomExceptions.NotOrderOwnerException("you dont own this order!");

        return new ResponseEntity<>(orderMapper.orderToDto(orderService.statusToStarted(orderId)), HttpStatus.OK);
    }

    @PostMapping("/changeState/finished")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderResponseDto> changeStateFinished(@RequestParam Long orderId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findByUsername(username);

        if (!Objects.equals(orderService.findById(orderId).getCustomer().getId(), customer.getId()))
            throw new CustomExceptions.NotOrderOwnerException("you dont own this order!");

        return new ResponseEntity<>(orderMapper.orderToDto(orderService.statusToFinished(orderId)), HttpStatus.OK);
    }

    @PostMapping("/changeState/paid")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderResponseDto> changeStatePaid(@RequestParam Long orderId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findByUsername(username);

        if (!Objects.equals(orderService.findById(orderId).getCustomer().getId(), customer.getId()))
            throw new CustomExceptions.NotOrderOwnerException("you dont own this order!");

        return new ResponseEntity<>(orderMapper.orderToDto(orderService.statusToPaid(orderId)), HttpStatus.OK);
    }
}
