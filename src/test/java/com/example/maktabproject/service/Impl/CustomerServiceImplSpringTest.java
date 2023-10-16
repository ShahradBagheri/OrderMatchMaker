package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplSpringTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    void validCustomerRegisterShouldSave() {

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahrad2@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customer = customerService.register(customer);
        assertThat(customer.getId()).isNotNull();
    }

    @Test
    void duplicateEmailShouldNotSave(){
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
        assertThat(customer.getId()).isNull();
    }

    @Test
    void invalidPasswordShouldNotSave(){
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahrad2@gmaill.com")
                .password("qweasdqwe")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customerService.register(customer);
        assertThat(customer.getId()).isNull();
    }

    @Test
    void invalidEmailShouldNotSave(){
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahrad2gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customerService.register(customer);
        assertThat(customer.getId()).isNull();
    }
}