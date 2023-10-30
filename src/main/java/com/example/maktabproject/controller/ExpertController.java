package com.example.maktabproject.controller;

import com.example.maktabproject.dto.CustomerResponseDto;
import com.example.maktabproject.dto.ExpertMapper;
import com.example.maktabproject.dto.ExpertResponseDto;
import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expert")
@RequiredArgsConstructor
public class ExpertController {

    private final ExpertServiceImpl expertService;
    private final ExpertMapper expertMapper;

    @PostMapping("/changePassword")
    public ResponseEntity<ExpertResponseDto> changePassword(@RequestParam Long customerId, @RequestParam String newPassword) throws ExpertNotFoundException {

        return new ResponseEntity<>(expertMapper.expertToDto(expertService.changePassword(customerId,newPassword)), HttpStatus.OK);
    }
}
