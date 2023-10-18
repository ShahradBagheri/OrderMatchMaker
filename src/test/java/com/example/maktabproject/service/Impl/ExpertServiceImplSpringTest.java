package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpertServiceImplSpringTest {

    @Autowired
    private ExpertServiceImpl expertService;

    @Test
    void validExpertRegisterShouldSave() {

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("expertShouldSave@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);
        assertThat(expert.getId()).isNotNull();
    }

    @Test
    void duplicateEmailShouldNotSave() {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("expertfirst@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        Expert dupExpert = Expert.builder()
                .user(user)
                .build();

        expertService.register(expert);
        expertService.register(dupExpert);
        assertThat(dupExpert.getId()).isNull();
    }
}