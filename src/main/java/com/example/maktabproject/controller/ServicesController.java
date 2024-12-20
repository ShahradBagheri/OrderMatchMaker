package com.example.maktabproject.controller;


import com.example.maktabproject.model.dto.mainSevice.MainServiceMapper;
import com.example.maktabproject.model.dto.mainSevice.MainServiceResponseDto;
import com.example.maktabproject.model.dto.subService.SubServiceMapper;
import com.example.maktabproject.model.dto.subService.SubServiceResponseDto;
import com.example.maktabproject.model.dto.subService.SubServiceViewDToResponseDto;
import com.example.maktabproject.service.MainServiceService;
import com.example.maktabproject.service.SubServiceService;
import com.example.maktabproject.service.SubServiceViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServicesController {

    private final SubServiceViewService subServiceViewService;
    private final SubServiceService subServiceService;
    private final MainServiceService mainServiceService;
    private final SubServiceMapper subServiceMapper;
    private final MainServiceMapper mainServiceMapper;


    @GetMapping("/subService/loadALl")
    public ResponseEntity<List<SubServiceViewDToResponseDto>> loadAllSubServices(){
        List<SubServiceViewDToResponseDto> subServiceViewDToResponseDtos = subServiceViewService.loadAll().stream()
                .map(subServiceMapper::subServiceViewToDto)
                .toList();
        return new ResponseEntity<>(subServiceViewDToResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/subService/loadALl/{mainServiceId}")
    public ResponseEntity<List<SubServiceResponseDto>> loadAllSubServicesByMainService(@PathVariable Long mainServiceId){
        List<SubServiceResponseDto> subServiceViewDToResponseDtos = subServiceService.findByMainService(mainServiceId)
                .stream()
                .map(subServiceMapper::subServiceToDto)
                .toList();
        return new ResponseEntity<>(subServiceViewDToResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/mainService/loadAll")
    public ResponseEntity<List<MainServiceResponseDto>> loadAllMainServices(){
        List<MainServiceResponseDto> mainServiceResponseDtos = mainServiceService.findAll().stream()
                .map(mainServiceMapper::mainServiceToDto)
                .toList();
        return new ResponseEntity<>(mainServiceResponseDtos, HttpStatus.OK);
    }
}
