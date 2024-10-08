package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class UserServiceImplSpringTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    void userShouldLogin() throws CustomExceptions.IncorrectCredentialsException {
        User user = User.builder()
                .firstname("user test")
                .lastname("test user")
                .email("user_test@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customer = customerService.register(customer);

        User loggedInUser = userService.login(user.getEmail(),user.getPassword());
        assertThat(loggedInUser).isEqualTo(user);
    }

    @Test
    void userShouldNotLogin(){
        assertThatThrownBy( () -> userService.login("","")).isInstanceOf(CustomExceptions.IncorrectCredentialsException.class);
    }
}