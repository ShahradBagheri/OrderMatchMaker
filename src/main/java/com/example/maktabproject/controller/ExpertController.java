package com.example.maktabproject.controller;

import com.example.maktabproject.dto.*;
import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import com.example.maktabproject.service.Impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expert")
@RequiredArgsConstructor
public class ExpertController {

    private final ExpertServiceImpl expertService;
    private final ExpertMapper expertMapper;
    private final OrderMapper orderMapper;
    private final OrderServiceImpl orderService;

    @PostMapping("/changePassword")
    @PreAuthorize("hasRole('EXPERT')")
    public ResponseEntity<ExpertResponseDto> changePassword(@RequestParam String newPassword) throws ExpertNotFoundException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long expertId = expertService.findByUsername(username).getId();

        return new ResponseEntity<>(expertMapper.expertToDto(expertService.changePassword(expertId, newPassword)), HttpStatus.OK);
    }

    @GetMapping("/checkScore")
    @PreAuthorize("hasRole('EXPERT')")
    public float checkScore() throws ExpertNotFoundException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long expertId = expertService.findByUsername(username).getId();

        return expertService.findById(expertId).getScore();
    }

    @GetMapping("/filter/order")
    @PreAuthorize("hasRole('EXPERT')")
    public ResponseEntity<List<OrderResponseDto>> filterOrders(@RequestBody UserOrderFilterRequestDto customerOrderFilterRequest){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Expert expert = expertService.findByUsername(username);

        return new ResponseEntity<>(orderService.filterOrderCustomer(expert.getId(),customerOrderFilterRequest)
                .stream()
                .map(orderMapper::orderToDto)
                .toList()
                ,HttpStatus.OK);
    }
}
