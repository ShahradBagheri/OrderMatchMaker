package com.example.maktabproject.controller;

import com.example.maktabproject.dto.*;
import com.example.maktabproject.exception.MainServiceNotFoundException;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.service.AdminService;
import com.example.maktabproject.service.Impl.MainServiceServiceImpl;
import com.example.maktabproject.service.Impl.SubServiceServiceImpl;
import com.example.maktabproject.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final MainServiceServiceImpl mainServiceService;
    private final SubServiceServiceImpl subServiceService;
    private final MainServiceMapper mainServiceMapper;
    private final SubServiceMapper subServiceMapper;

    @GetMapping("/allMainService")
    public List<MainServiceResponseDto> getMainServices(){

        List<MainService> mainServiceList = mainServiceService.findAll();
        List<MainServiceResponseDto> list = mainServiceList.stream().map(mainServiceMapper::mainServiceToDto).toList();
        return list;
    }

    @PostMapping("/register/MainService")
    public ResponseEntity<MainServiceResponseDto> registerMainService(@RequestBody MainServiceRequestDto mainServiceRequestDto){

        MainService mainService = mainServiceService.register(mainServiceMapper.mainServiceDtoToMainService(mainServiceRequestDto));
        return new ResponseEntity<>(mainServiceMapper.mainServiceToDto(mainService), HttpStatus.OK) ;
    }

    @PostMapping("/register/SubService")
    public ResponseEntity<SubServiceResponseDto> registerSubService(@RequestBody SubServiceRequestDto subServiceRequestDto,
                                         @RequestParam Long mainServiceId) throws MainServiceNotFoundException {

        SubService subService = subServiceMapper.subServiceDtoToSubService(subServiceRequestDto);
        subService.setMainService(mainServiceService.findById(mainServiceId));

        return new ResponseEntity<>(subServiceMapper.subServiceToDto(subServiceService.register(subService)), HttpStatus.OK) ;
    }
}
