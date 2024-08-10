package com.example.maktabproject.controller;


import com.example.maktabproject.model.dto.subService.SubServiceMapper;
import com.example.maktabproject.model.dto.subService.SubServiceViewDToResponseDto;
import com.example.maktabproject.service.SubServiceViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServicesController {

    private final SubServiceViewService subServiceViewService;
    private final SubServiceMapper subServiceMapper;

    @GetMapping("/subService/loadALl")
    public ResponseEntity<List<SubServiceViewDToResponseDto>> loadAllSubServices(){
        List<SubServiceViewDToResponseDto> subServiceViewDToResponseDtos = subServiceViewService.loadAll().stream()
                .map(subServiceMapper::subServiceViewToDto)
                .toList();
        return new ResponseEntity<>(subServiceViewDToResponseDtos, HttpStatus.OK);
    }
}
