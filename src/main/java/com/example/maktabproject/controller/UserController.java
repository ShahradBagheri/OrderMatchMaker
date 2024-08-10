package com.example.maktabproject.controller;

import com.example.maktabproject.model.dto.customer.CustomerMapper;
import com.example.maktabproject.model.dto.customer.CustomerRequestDto;
import com.example.maktabproject.model.dto.customer.CustomerResponseDto;
import com.example.maktabproject.model.dto.expert.ExpertMapper;
import com.example.maktabproject.model.dto.expert.ExpertRequestDto;
import com.example.maktabproject.model.dto.expert.ExpertResponseDto;
import com.example.maktabproject.model.dto.user.UserLoginDto;
import com.example.maktabproject.model.dto.user.UserMapper;
import com.example.maktabproject.model.dto.user.UserResponseDto;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.VerificationToken;
import com.example.maktabproject.model.enums.ExpertStatus;
import com.example.maktabproject.model.enums.Role;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import com.example.maktabproject.service.Impl.UserServiceImpl;
import com.example.maktabproject.service.Impl.VerificationTokenServiceImpl;
import com.example.maktabproject.util.ImageProcessing;
import com.example.maktabproject.util.TokenEmail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


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
    private final TokenEmail tokenEmail;
    private final VerificationTokenServiceImpl verificationTokenService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginDto userLoginDto) {

        User user = userService.login(userLoginDto.email(), userLoginDto.password());
        UserResponseDto userResponseDto = userMapper.userToDto(user);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping(path = "/register/customer", consumes = "application/json")
    public CustomerResponseDto customerRegister(@RequestBody @Valid CustomerRequestDto customerRequestDto) {

        Customer customer = customerMapper.dtoToCustomer(customerRequestDto);
        customer = customerService.register(customer);
        tokenEmail.sendEmail(customer.getUser());
        return customerMapper.customerToDto(customer);
    }

    @PostMapping(value = "/register/expert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ExpertResponseDto expertRegister(@ModelAttribute ExpertRequestDto expertRequestDto) {

        byte[] imageData = imageProcessing.imageToBytes(expertRequestDto.image());
        Expert expert = expertMapper.dtoToExpert(expertRequestDto);
        expert.setImageData(imageData);
        expert.setExpertStatus(ExpertStatus.NEW);
        expert.setScore(0F);
        expert = expertService.register(expert);
        tokenEmail.sendEmail(expert.getUser());
        return expertMapper.expertToDto(expert);
    }

    @GetMapping(value = "/verify")
    public String verifyAccount(@RequestParam String token) {

        VerificationToken verificationToken = verificationTokenService.findByToken(token);

        if (verificationToken.getUser().isEnabled())
            return "already verified";

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userService.register(user);
        if(user.getRole().equals(Role.ROLE_EXPERT)){
            Expert expert = expertService.findByUser(user.getId());
            expert.setExpertStatus(ExpertStatus.AWAITING_CONFIRMATION);
            expertService.register(expert);
        }
        return "DONE!";
    }
}
