package com.example.maktabproject.controller;

import com.example.maktabproject.model.dto.expert.ExpertMapper;
import com.example.maktabproject.model.dto.expert.ExpertResponseDto;
import com.example.maktabproject.model.dto.order.OrderMapper;
import com.example.maktabproject.model.dto.order.OrderResponseDto;
import com.example.maktabproject.model.dto.user.UserOrderFilterRequestDto;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.enums.ExpertStatus;
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
    public ResponseEntity<ExpertResponseDto> changePassword(@RequestParam String newPassword) {
        if(!newPassword.matches("^(?=.*[a-zA-Z])(?=.*\\d).{8,}$"))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Expert expert = expertService.findByUsername(username);

        if(expert.getExpertStatus() != ExpertStatus.APPROVED)
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(expertMapper.expertToDto(expertService.changePassword(expert.getId(), newPassword)), HttpStatus.OK);
    }

    @GetMapping("/checkScore")
    @PreAuthorize("hasRole('EXPERT')")
    public ResponseEntity<Float> checkScore() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Expert expert = expertService.findByUsername(username);

        if(expert.getExpertStatus() != ExpertStatus.APPROVED)
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(expertService.findById(expert.getId()).getScore(),HttpStatus.OK) ;
    }

    @GetMapping("/filter/order")
    @PreAuthorize("hasRole('EXPERT')")
    public ResponseEntity<List<OrderResponseDto>> filterOrders(@RequestBody UserOrderFilterRequestDto userOrderFilterRequestDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Expert expert = expertService.findByUsername(username);

        if(expert.getExpertStatus() != ExpertStatus.APPROVED)
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(orderService.filterOrderExpert(expert.getId(), userOrderFilterRequestDto)
                .stream()
                .map(orderMapper::orderToDto)
                .toList()
                , HttpStatus.OK);
    }

    @GetMapping("/balance")
    @PreAuthorize("hasRole('EXPERT')")
    public ResponseEntity<Double> getBalance(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Expert expert = expertService.findByUsername(username);

        if(expert.getExpertStatus() != ExpertStatus.APPROVED)
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(expert.getUser().getWallet().getCredit(),HttpStatus.OK);
    }
}
