package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.InvalidPriceException;
import com.example.maktabproject.exception.InvalidTimeException;
import com.example.maktabproject.exception.OfferNotFoundException;
import com.example.maktabproject.model.*;
import com.example.maktabproject.model.enumeration.OrderState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OfferServiceImplTest {

    @Autowired
    private OfferServiceImpl offerService;

    @Autowired
    private ExpertServiceImpl expertService;

    @Autowired
    private SubServiceServiceImpl subServiceService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    void validOfferShouldSave() throws InvalidPriceException, InvalidTimeException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("offersavetest@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);

        SubService subService = SubService.builder()
                .name("testingofferregister")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("testingofferaddsavecustomer@gmaill.com")
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
        assertThat(offer.getId()).isNotNull();
    }

    @Test
    void offerWithNoExpertShouldNotSave() throws InvalidPriceException, InvalidTimeException {

        SubService subService = SubService.builder()
                .name("noExpertOfferTest")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("noExpertOfferTest@gmaill.com")
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
                .order(order)
                .suggestedPrice(200.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .completionDate(LocalDateTime.now().plusDays(3))
                .build();

        offer = offerService.register(offer);
        assertThat(offer).isNull();
    }

    @Test
    void invalidTimeShouldNotSave() throws InvalidPriceException, InvalidTimeException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("invalidTimeShouldNotSave2@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);

        SubService subService = SubService.builder()
                .name("invalidTimeShouldNotSave")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("invalidTimeShouldNotSave@gmaill.com")
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
                .startingDate(LocalDateTime.now().minusDays(1))
                .completionDate(LocalDateTime.now().plusDays(3))
                .build();

        assertThatThrownBy(() -> offerService.register(offer)).isInstanceOf(InvalidTimeException.class);
    }

    @Test
    void invalidPriceShouldNotBeSaved() throws InvalidPriceException, InvalidTimeException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("invalidPriceSHouldNotBeSavedOffer@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);

        SubService subService = SubService.builder()
                .name("invalidPriceSHouldNotBeSavedOffer")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("invalidPriceSHouldNotBeSavedOffer2@gmaill.com")
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
                .suggestedPrice(50.0)
                .startingDate(LocalDateTime.now().minusDays(1))
                .completionDate(LocalDateTime.now().plusDays(3))
                .build();

        assertThatThrownBy(() -> offerService.register(offer)).isInstanceOf(InvalidPriceException.class);
    }

    @Test
    void shouldFindOffer() throws InvalidPriceException, InvalidTimeException, OfferNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shouldfindoffertest@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);

        SubService subService = SubService.builder()
                .name("shouldfindoffertest")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shouldfindoffertest2@gmaill.com")
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
        assertThat(offerService.findById(offer.getId()).getId()).isNotNull();
    }

    @Test
    void offerShouldGetDeleted() throws InvalidPriceException, InvalidTimeException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("testingdeleteforoffer@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);

        SubService subService = SubService.builder()
                .name("testingdeleteforoffer")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("testingdeleteforoffer2@gmaill.com")
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
        long id = offer.getId();

        offerService.delete(offer);
        assertThatThrownBy(() -> offerService.findById(id)).isInstanceOf(OfferNotFoundException.class);
    }

    @Test
    void findByIdShouldThrowNotFound(){

        assertThatThrownBy(() -> offerService.findById(10000L)).isInstanceOf(OfferNotFoundException.class);
    }

    @Test
    void shouldFindAll() throws InvalidPriceException, InvalidTimeException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("testingfindAllforOffer@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);

        SubService subService = SubService.builder()
                .name("testingfindAllforOffer")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("testingfindAllforOffer2@gmaill.com")
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

        List<Offer> all = offerService.findAll();
        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    void findAllByCustomerOrderByPrice() throws InvalidPriceException, InvalidTimeException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("findAllByCUstomerOffer@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .score(3F)
                .build();

        expert = expertService.register(expert);

        User user3 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("findAllByCUstomerOffer3@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert2 = Expert.builder()
                .user(user3)
                .score(1F)
                .build();

        expert2 = expertService.register(expert2);

        SubService subService = SubService.builder()
                .name("testingfindAllforOffer123")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("findAllByCUstomerOffer1@gmaill.com")
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

        Offer offer2 = Offer.builder()
                .expert(expert2)
                .order(order)
                .suggestedPrice(250.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .completionDate(LocalDateTime.now().plusDays(3))
                .build();

        offer = offerService.register(offer);
        offer2 = offerService.register(offer2);
        assertThat(offerService.findByCustomerPriceOrder(customer).stream().map(Offer::getId)).isEqualTo(List.of(offer.getId(),offer2.getId()));
    }

    @Test
    void findAllByCustomerOrderByScore() throws InvalidPriceException, InvalidTimeException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("somethingsomethingtest1@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .score(3F)
                .build();

        expert = expertService.register(expert);

        User user3 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("somethingsomethingtest12@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert2 = Expert.builder()
                .user(user3)
                .score(1F)
                .build();

        expert2 = expertService.register(expert2);

        SubService subService = SubService.builder()
                .name("somethingsomethingtest13")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("somethingsomethingtest14@gmaill.com")
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

        Offer offer2 = Offer.builder()
                .expert(expert2)
                .order(order)
                .suggestedPrice(250.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .completionDate(LocalDateTime.now().plusDays(3))
                .build();

        offer = offerService.register(offer);
        offer2 = offerService.register(offer2);
        assertThat(offerService.findByCustomerScoreOrder(customer).stream().map(Offer::getId)).isEqualTo(List.of(offer2.getId(),offer.getId()));
    }
}