package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.*;
import com.example.maktabproject.model.enums.OrderState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void validOfferShouldSave() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.OrderNotFoundException {
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
    void offerWithNoExpertShouldNotSave() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.OrderNotFoundException {

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
    void invalidTimeShouldNotSave() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException {
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

        assertThatThrownBy(() -> offerService.register(offer)).isInstanceOf(CustomExceptions.InvalidTimeException.class);
    }

    @Test
    void invalidPriceShouldNotBeSaved() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException {
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

        assertThatThrownBy(() -> offerService.register(offer)).isInstanceOf(CustomExceptions.InvalidPriceException.class);
    }

    @Test
    void shouldFindOffer() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.OfferNotFoundException, CustomExceptions.OrderNotFoundException {
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
    void offerShouldGetDeleted() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.OrderNotFoundException {
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
        assertThatThrownBy(() -> offerService.findById(id)).isInstanceOf(CustomExceptions.OfferNotFoundException.class);
    }

    @Test
    void findByIdShouldThrowNotFound(){

        assertThatThrownBy(() -> offerService.findById(10000L)).isInstanceOf(CustomExceptions.OfferNotFoundException.class);
    }

    @Test
    void shouldFindAll() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.OrderNotFoundException {
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
    void findAllByCustomerOrderByPrice() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.CustomerNotFoundException, CustomExceptions.OrderNotFoundException {
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
        assertThat(offerService.findForCustomerPriceOrder(customer.getId()).stream().map(Offer::getId)).isEqualTo(List.of(offer.getId(),offer2.getId()));
    }

    @Test
    void findAllByCustomerOrderByScore() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.CustomerNotFoundException, CustomExceptions.OrderNotFoundException {
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
        assertThat(offerService.findForCustomerScoreOrder(customer.getId()).stream().map(Offer::getId)).isEqualTo(List.of(offer2.getId(),offer.getId()));
    }
}