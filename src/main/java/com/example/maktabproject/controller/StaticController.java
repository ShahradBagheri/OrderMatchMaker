package com.example.maktabproject.controller;


import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.dto.customer.CustomerMapper;
import com.example.maktabproject.model.dto.customer.CustomerRequestDto;
import com.example.maktabproject.model.dto.expert.ExpertMapper;
import com.example.maktabproject.model.dto.expert.ExpertRequestDto;
import com.example.maktabproject.model.dto.mainSevice.MainServiceMapper;
import com.example.maktabproject.model.dto.mainSevice.MainServiceRequestDto;
import com.example.maktabproject.model.enums.ExpertStatus;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import com.example.maktabproject.service.Impl.MainServiceServiceImpl;
import com.example.maktabproject.util.ImageProcessing;
import com.example.maktabproject.util.TokenEmail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/static")
public class StaticController {

    private final CustomerMapper customerMapper;
    private final CustomerServiceImpl customerService;
    private final TokenEmail tokenEmail;
    private final ExpertMapper expertMapper;
    private final ExpertServiceImpl expertService;
    private final ImageProcessing imageProcessing;

    @GetMapping("/login")
    @PostMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "logout", required = false) String logout,
                             Model model){
        if (error != null) {
            model.addAttribute("loginError", true);
        }
        if (logout != null) {
            model.addAttribute("logoutSuccess", true);
        }
        return "login";
    }

    @PostMapping(path = "/register/customer", consumes = "application/x-www-form-urlencoded")
    public String customerRegisterStatic(@Valid CustomerRequestDto customerRequestDto) {
        try{
            Customer customer = customerMapper.dtoToCustomer(customerRequestDto);
            customer = customerService.register(customer);
            tokenEmail.sendEmail(customer.getUser());
            return "successfulSignup";
        } catch (Exception e){
            throw new CustomExceptions.CustomerRegistrationException("Username or Email has already been taken!");
        }
    }

    @PostMapping(value = "/register/expert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String expertRegister(@ModelAttribute ExpertRequestDto expertRequestDto) {
        try{
            byte[] imageData = imageProcessing.imageToBytes(expertRequestDto.image());
            Expert expert = expertMapper.dtoToExpert(expertRequestDto);
            expert.setImageData(imageData);
            expert.setExpertStatus(ExpertStatus.NEW);
            expert.setScore(0F);
            expert = expertService.register(expert);
            tokenEmail.sendEmail(expert.getUser());
            return "successfulSignup";
        } catch (Exception e) {
            throw new CustomExceptions.ExpertRegistrationException("Username or Email has already been taken!");
        }
    }

    @GetMapping(value = "/adminPanel")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPanel(){
        return "adminPanel";
    }

    @GetMapping(value = "/expertPanel")
    @PreAuthorize("hasRole('EXPERT')")
    public String expertPanel(){
        return "expertPanel";
    }

    @GetMapping(value = "/customerPanel")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String customerPanel(){
        return "customerPanel";
    }
}
