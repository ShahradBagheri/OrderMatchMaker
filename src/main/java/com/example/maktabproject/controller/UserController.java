package com.example.maktabproject.controller;

import com.example.maktabproject.dto.*;
import com.example.maktabproject.exception.ImageToBigException;
import com.example.maktabproject.exception.IncorrectCredentialsException;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import com.example.maktabproject.service.Impl.UserServiceImpl;
import com.example.maktabproject.util.ImageProcessing;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final CustomerMapper customerMapper;
    private final ExpertMapper expertMapper;
    private final CustomerServiceImpl customerService;
    private final ExpertServiceImpl expertService;
    private final ImageProcessing imageProcessing;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginDto userLoginDto) throws IncorrectCredentialsException {

        User user = userService.login(userLoginDto.email(), userLoginDto.password());
        UserResponseDto userResponseDto = userMapper.userToDto(user);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping("/register/customer")
    public CustomerResponseDto customerRegister(@RequestBody @Valid CustomerRequestDto customerRequestDto){

        Customer customer = customerMapper.dtoToCustomer(customerRequestDto);
        customer = customerService.register(customer);
        return customerMapper.customerToDto(customer);
    }

    @PostMapping(value = "/register/expert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ExpertResponseDto expertRegister(@ModelAttribute ExpertRequestDto expertRequestDto) throws ImageToBigException {

        byte[] imageData = imageProcessing.imageToBytes(expertRequestDto.image());
        Expert expert = expertMapper.dtoToExpert(expertRequestDto);
        expert.setImageData(imageData);
        expert = expertService.register(expert);
        return expertMapper.expertToDto(expert.getUser());
    }
}
