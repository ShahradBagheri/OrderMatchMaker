package com.example.maktabproject.controller;

import com.example.maktabproject.dto.CustomerMapper;
import com.example.maktabproject.dto.CustomerResponseDto;
import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final CustomerMapper customerMapper;

    @PostMapping("/changePassword")
    public ResponseEntity<CustomerResponseDto> changePassword(@RequestParam Long customerId,@RequestParam String newPassword) throws CustomerNotFoundException {

        return new ResponseEntity<>(customerMapper.customerToDto(customerService.changePassword(customerId,newPassword)), HttpStatus.OK);
    }
}
