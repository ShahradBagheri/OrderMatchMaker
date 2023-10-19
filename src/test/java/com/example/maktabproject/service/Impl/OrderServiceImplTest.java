package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.InvalidPriceException;
import com.example.maktabproject.exception.InvalidTimeException;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.enumeration.OrderState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private SubServiceServiceImpl subServiceService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    void orderShouldSave() throws InvalidPriceException, InvalidTimeException {

        SubService subService = SubService.builder()
                .name("orderShouldSaveTest")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("registerOrderTest@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customer = customerService.register(customer);

        Order order = Order.builder()
                .orderState(OrderState.STARTED)
                .address("Some address")
                .subService(subService)
                .customer(customer)
                .suggestedPrice(200.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        order = orderService.register(order);
        assertThat(order).isNotNull();
    }

    @Test
    void invalidPriceShouldNotSave() throws InvalidPriceException {

        SubService subService = SubService.builder()
                .name("orderShouldNotSavePrice")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("failPriceValidation@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customer = customerService.register(customer);

        Order order = Order.builder()
                .orderState(OrderState.STARTED)
                .address("Some address")
                .subService(subService)
                .customer(customer)
                .suggestedPrice(50.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        assertThatThrownBy( () -> orderService.register(order)).isInstanceOf(InvalidPriceException.class);
    }

    @Test
    void invalidTimeShouldNotSave() throws InvalidPriceException {

        SubService subService = SubService.builder()
                .name("orderShouldNotSaveTimer")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("failDateValidation@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customer = customerService.register(customer);

        Order order = Order.builder()
                .orderState(OrderState.STARTED)
                .address("Some address")
                .subService(subService)
                .customer(customer)
                .suggestedPrice(150.0)
                .startingDate(LocalDateTime.now().minusDays(1))
                .build();

        assertThatThrownBy( () -> orderService.register(order)).isInstanceOf(InvalidTimeException.class);
    }

    @Test
    void orderShouldNotSaveWithOutCustomer() throws InvalidPriceException, InvalidTimeException {

        SubService subService = SubService.builder()
                .name("noCustomer")
                .basePrice(100.0)
                .build();

        Order order = Order.builder()
                .suggestedPrice(150.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .subService(subService)
                .build();
        order = orderService.register(order);
        assertThat(order).isNull();
    }
}