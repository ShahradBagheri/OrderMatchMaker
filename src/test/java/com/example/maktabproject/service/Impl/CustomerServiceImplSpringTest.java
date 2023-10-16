package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplSpringTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    void register() {

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahrad2@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customerService.register(customer);
    }
}