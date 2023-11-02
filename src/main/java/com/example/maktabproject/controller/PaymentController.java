package com.example.maktabproject.controller;

import com.wf.captcha.SpecCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private Map<Integer,String> captchaMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    @GetMapping("/captcha")
    public Captcha generateCaptcha() {

        SpecCaptcha captcha = new SpecCaptcha(130,48);
        int id = counter.incrementAndGet();
        captchaMap.put(id, captcha.text());

        return new Captcha(id,captcha.toBase64());
    }

    record Captcha(Integer id,String base64){}
}
