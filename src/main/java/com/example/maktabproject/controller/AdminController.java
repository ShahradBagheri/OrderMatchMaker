package com.example.maktabproject.controller;

import com.example.maktabproject.dto.*;
import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.exception.MainServiceNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.enumeration.ExpertStatus;
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
    private final ExpertMapper expertMapper;

    @GetMapping("/allMainService")
    public List<MainServiceResponseDto> getMainServices(){

        List<MainService> mainServiceList = mainServiceService.findAll();
        List<MainServiceResponseDto> list = mainServiceList.stream().map(mainServiceMapper::mainServiceToDto).toList();
        return list;
    }

    @PostMapping("/register/mainService")
    public ResponseEntity<MainServiceResponseDto> registerMainService(@RequestBody MainServiceRequestDto mainServiceRequestDto){

        MainService mainService = mainServiceService.register(mainServiceMapper.mainServiceDtoToMainService(mainServiceRequestDto));
        return new ResponseEntity<>(mainServiceMapper.mainServiceToDto(mainService), HttpStatus.OK) ;
    }

    @PostMapping("/register/subService")
    public ResponseEntity<SubServiceResponseDto> registerSubService(@RequestBody SubServiceRequestDto subServiceRequestDto,
                                                                    @RequestParam Long mainServiceId) throws MainServiceNotFoundException {

        SubService subService = subServiceMapper.subServiceDtoToSubService(subServiceRequestDto);
        subService.setMainService(mainServiceService.findById(mainServiceId));

        return new ResponseEntity<>(subServiceMapper.subServiceToDto(subServiceService.register(subService)), HttpStatus.OK) ;
    }

    @PostMapping("/subService/editComment")
    public ResponseEntity<SubServiceResponseDto> editSubServiceComment(@RequestParam Long subServiceId,
                                                                       @RequestParam String newComment) throws SubServiceNotFoundException {

        return new ResponseEntity<>(subServiceMapper.subServiceToDto(adminService.editSubServiceComment(subServiceId,newComment)),HttpStatus.OK);
    }

    @PostMapping("/subService/editBasePrice")
    public ResponseEntity<SubServiceResponseDto> editSubServiceBasePrice(@RequestParam Long subServiceId,
                                                                       @RequestParam Double newBasePrice) throws SubServiceNotFoundException {

        return new ResponseEntity<>(subServiceMapper.subServiceToDto(adminService.editSubServicePrice(subServiceId,newBasePrice)),HttpStatus.OK);
    }

    @PostMapping("/expert/addToSubService")
    public ResponseEntity<ExpertResponseDto> addExpertToSubService(@RequestParam Long expertId,
                                                                   @RequestParam Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException {

        return new ResponseEntity<>(expertMapper.expertToDto(adminService.addExpertSubService(expertId,subServiceId)),HttpStatus.OK);
    }

    @DeleteMapping("/expert/removeToSubService")
    public ResponseEntity<ExpertResponseDto> removeExpertToSubService(@RequestParam Long expertId,
                                                                   @RequestParam Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException {

        return new ResponseEntity<>(expertMapper.expertToDto(adminService.removeExpertSubService(expertId,subServiceId)),HttpStatus.OK);
    }

    @PostMapping("/expert/approveExpert")
    public ResponseEntity<ExpertResponseDto> approveExpert(@RequestParam Long expertId) throws ExpertNotFoundException {

        return new ResponseEntity<>(expertMapper.expertToDto(adminService.updateExpertStatus(expertId, ExpertStatus.APPROVED)),HttpStatus.OK);
    }
}
