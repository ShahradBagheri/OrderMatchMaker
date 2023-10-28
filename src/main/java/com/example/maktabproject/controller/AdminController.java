package com.example.maktabproject.controller;

import com.example.maktabproject.dto.MainServiceMapper;
import com.example.maktabproject.dto.MainServiceRequestDto;
import com.example.maktabproject.dto.MainServiceResponseDto;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.service.AdminService;
import com.example.maktabproject.service.Impl.MainServiceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final MainServiceServiceImpl mainServiceService;
    private final MainServiceMapper mainServiceMapper;

    @GetMapping("/allMainService")
    public List<MainServiceResponseDto> getMainServices(){

        List<MainService> mainServiceList = mainServiceService.findAll();
        List<MainServiceResponseDto> list = mainServiceList.stream().map(mainServiceMapper::mainServiceToDto).toList();
        return list;
    }

    @PostMapping("/register/MainService")
    public MainServiceResponseDto registerMainService(@RequestBody MainServiceRequestDto mainServiceRequestDto){

        MainService mainService = mainServiceService.register(mainServiceMapper.mainServiceDtoToMainService(mainServiceRequestDto));
        return mainServiceMapper.mainServiceToDto(mainService);
    }
}
