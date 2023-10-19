package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.*;
import com.example.maktabproject.model.enumeration.OrderState;
import com.example.maktabproject.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

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

    @Autowired
    private ExpertServiceImpl expertService;

    @Autowired
    private OfferServiceImpl offerService;

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
                .name("notNullCheck")
                .basePrice(100.0)
                .build();

        subService = subServiceService.register(subService);

        Order order = Order.builder()
                .suggestedPrice(150.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .subService(subService)
                .build();
        order = orderService.register(order);
        assertThat(order).isNull();
    }

    @Test
    void orderShouldBeFound() throws ExpertNotFoundException, InvalidPriceException, InvalidTimeException, OrderNotFoundException {

        SubService subService = SubService.builder()
                .name("findByIdOrder")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("findByIdOrder@gmaill.com")
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
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        order = orderService.register(order);
        order = orderService.findById(order.getId());
        assertThat(order).isNotNull();
    }

    @Test
    void failToFindOrder() throws InvalidPriceException, InvalidTimeException, OrderNotFoundException {

        SubService subService = SubService.builder()
                .name("cantFindByIdOder")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("faildFind@gmaill.com")
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
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        order = orderService.register(order);
        long id = order.getId();
        orderService.delete(order);
        assertThatThrownBy(() -> orderService.findById(id)).isInstanceOf(OrderNotFoundException.class);
    }

    @Test
    void allOrdersShouldBeFound() throws InvalidPriceException, InvalidTimeException {

        SubService subService1 = SubService.builder()
                .name("findAll1")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService1);

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("findAll1@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customer = customerService.register(customer);

        Order order = Order.builder()
                .orderState(OrderState.STARTED)
                .address("Some address")
                .subService(subService1)
                .customer(customer)
                .suggestedPrice(150.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        order = orderService.register(order);

        List<Order> all = orderService.findAll();
        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    void orderWithNoTimeOrPriceShouldNotSave() throws InvalidPriceException, InvalidTimeException {
        Order order = Order.builder()
                .orderState(OrderState.STARTED)
                .address("Some address")
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        assertThatThrownBy(() -> orderService.register(order)).isInstanceOf(InvalidPriceException.class);
    }

    @Test
    void findOrdersForExpert() throws SubServiceNotFoundException, InvalidPriceException, InvalidTimeException, ExpertNotFoundException {
        SubService subService = SubService.builder()
                .name("ExpertFindingSubService")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("ExpertFindingEmail@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customer = customerService.register(customer);

        Order order = Order.builder()
                .orderState(OrderState.WAITING_FOR_SUGGESTIONS)
                .address("Some address")
                .subService(subService)
                .customer(customer)
                .suggestedPrice(200.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        order = orderService.register(order);

        SubService subService2 = SubService.builder()
                .name("orderShouldSaveTest2")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService2);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("registerOrderTest2@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer2 = Customer.builder()
                .user(user2)
                .build();

        customer2 = customerService.register(customer2);

        Order order2 = Order.builder()
                .orderState(OrderState.WAITING_TO_SELECT_SUGGESTION)
                .address("Some address")
                .subService(subService)
                .customer(customer)
                .suggestedPrice(200.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        order = orderService.register(order2);

        User user3 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("expertOrderFinder@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user3)
                .build();

        expert = expertService.register(expert);
        expert = expertService.addSubService(expert,subService);
        expert = expertService.addSubService(expert,subService2);

        assertThat(orderService.findOrdersForExpert(expert).size()).isGreaterThan(1);
    }

    @Test
    void orderStatusChangeAfterOneOffer() throws InvalidPriceException, InvalidTimeException, OrderNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("orderstatuschangeoffer@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);

        SubService subService = SubService.builder()
                .name("orderstatuschangeoffer")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("orderstatuschangeoffer2@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user2)
                .build();

        customer = customerService.register(customer);

        Order order = Order.builder()
                .orderState(OrderState.WAITING_FOR_SUGGESTIONS)
                .address("Some address")
                .subService(subService)
                .customer(customer)
                .suggestedPrice(200.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        orderService.register(order);

        Offer offer = Offer.builder()
                .expert(expert)
                .order(order)
                .suggestedPrice(200.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .completionDate(LocalDateTime.now().plusDays(3))
                .build();

        offer = offerService.register(offer);
        order = orderService.updateOrderStatus(order);
        assertThat(order.getOrderState()).isEqualTo(OrderState.WAITING_TO_SELECT_SUGGESTION);
    }
}