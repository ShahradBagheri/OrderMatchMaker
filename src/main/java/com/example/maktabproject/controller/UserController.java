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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final CustomerServiceImpl customerService;
    private final ExpertServiceImpl expertService;
    private final ImageProcessing imageProcessing;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginDto userLoginDto) throws IncorrectCredentialsException {

        User user = userService.login(userLoginDto.email(), userLoginDto.password());
        UserResponseDto userResponseDto = userMapper.userToUserDto(user);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping("/register/customer")
    public UserResponseDto customerRegister(@RequestBody @Valid UserRequestDto userRequestDto){

        Customer customer = userMapper.userDtoToCustomer(userRequestDto);
        customer = customerService.register(customer);
        return userMapper.userToUserDto(customer.getUser());
    }

    @PostMapping("/register/expert")
    public UserResponseDto expertRegister(@RequestBody @Valid UserRequestDto userRequestDto, @RequestPart MultipartFile image) throws IOException, ImageToBigException {

        byte[] imageData = imageProcessing.imageToBytes(image);
        Expert expert = userMapper.userDtoToExpert(userRequestDto);
        expert.setImageData(imageData);
        expert = expertService.register(expert);
        return userMapper.userToUserDto(expert.getUser());
    }
}
