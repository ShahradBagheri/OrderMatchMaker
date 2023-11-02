package com.example.maktabproject.controller;

import com.example.maktabproject.dto.PaymentRequestDto;
import com.example.maktabproject.exception.InvalidCaptchaException;
import com.example.maktabproject.exception.UserNotFoundException;
import com.example.maktabproject.model.User;
import com.example.maktabproject.service.Impl.UserServiceImpl;
import com.wf.captcha.SpecCaptcha;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final Map<Integer, String> captchaMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger();
    private final UserServiceImpl userService;

    @PostMapping("/submit")
    public String payment(@RequestBody @Valid PaymentRequestDto paymentRequestDto) throws InvalidCaptchaException, UserNotFoundException {

        String captchaText = captchaMap.get(paymentRequestDto.captchaId());
        if (!paymentRequestDto.captcha().toUpperCase().equals(captchaText))
            throw new InvalidCaptchaException();

        User user = userService.findById(paymentRequestDto.userId());
        user.getWallet().setCredit(user.getWallet().getCredit() + paymentRequestDto.amount());
        userService.register(user);
        return "MONEY ADDED!";
    }

    @GetMapping("/captcha")
    public Captcha generateCaptcha() {

        SpecCaptcha captcha = new SpecCaptcha(130, 48);
        int id = counter.incrementAndGet();
        captchaMap.put(id, captcha.text().toUpperCase());

        return new Captcha(id, captcha.toBase64());
    }

    record Captcha(Integer id, String base64) {
    }
}
