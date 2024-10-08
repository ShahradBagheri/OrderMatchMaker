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
class OrderServiceImplTest {

    @Autowired
    private OfferServiceImpl offerService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private SubServiceServiceImpl subServiceService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private ExpertServiceImpl expertService;

    @Test
    void orderShouldSave() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException {

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
    void invalidPriceShouldNotSave() throws CustomExceptions.InvalidPriceException {

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

        assertThatThrownBy( () -> orderService.register(order)).isInstanceOf(CustomExceptions.InvalidPriceException.class);
    }

    @Test
    void invalidTimeShouldNotSave() throws CustomExceptions.InvalidPriceException {

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

        assertThatThrownBy( () -> orderService.register(order)).isInstanceOf(CustomExceptions.InvalidTimeException.class);
    }

    @Test
    void orderShouldNotSaveWithOutCustomer() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException {

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
    void orderShouldBeFound() throws CustomExceptions.ExpertNotFoundException, CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.OrderNotFoundException {

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
    void failToFindOrder() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.OrderNotFoundException {

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
        assertThatThrownBy(() -> orderService.findById(id)).isInstanceOf(CustomExceptions.OrderNotFoundException.class);
    }

    @Test
    void allOrdersShouldBeFound() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException {

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
    void orderWithNoTimeOrPriceShouldNotSave() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException {
        Order order = Order.builder()
                .orderState(OrderState.STARTED)
                .address("Some address")
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        assertThatThrownBy(() -> orderService.register(order)).isInstanceOf(CustomExceptions.InvalidPriceException.class);
    }

    @Test
    void findOrdersForExpert() throws CustomExceptions.SubServiceNotFoundException, CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.ExpertNotFoundException {
//        SubService subService = SubService.builder()
//                .name("ExpertFindingSubService")
//                .basePrice(100.0)
//                .build();
//
//        subServiceService.register(subService);
//
//        User user = User.builder()
//                .firstname("shahrad")
//                .lastname("bagheri")
//                .email("ExpertFindingEmail@gmaill.com")
//                .password("qweasd123")
//                .build();
//        Customer customer = Customer.builder()
//                .user(user)
//                .build();
//
//        customer = customerService.register(customer);
//
//        Order order = Order.builder()
//                .orderState(OrderState.WAITING_FOR_SUGGESTIONS)
//                .address("Some address")
//                .subService(subService)
//                .customer(customer)
//                .suggestedPrice(200.0)
//                .startingDate(LocalDateTime.now().plusDays(1))
//                .build();
//
//        order = orderService.register(order);
//
//        SubService subService2 = SubService.builder()
//                .name("orderShouldSaveTest2")
//                .basePrice(100.0)
//                .build();
//
//        subServiceService.register(subService2);
//
//        User user2 = User.builder()
//                .firstname("shahrad")
//                .lastname("bagheri")
//                .email("registerOrderTest2@gmaill.com")
//                .password("qweasd123")
//                .build();
//        Customer customer2 = Customer.builder()
//                .user(user2)
//                .build();
//
//        customer2 = customerService.register(customer2);
//
//        Order order2 = Order.builder()
//                .orderState(OrderState.WAITING_TO_SELECT_SUGGESTION)
//                .address("Some address")
//                .subService(subService)
//                .customer(customer)
//                .suggestedPrice(200.0)
//                .startingDate(LocalDateTime.now().plusDays(1))
//                .build();
//
//        order = orderService.register(order2);
//
//        User user3 = User.builder()
//                .firstname("shahrad")
//                .lastname("bagheri")
//                .email("expertOrderFinder@gmaill.com")
//                .password("qweasd123")
//                .build();
//        Expert expert = Expert.builder()
//                .user(user3)
//                .build();
//
//        expert = expertService.register(expert);
//        expert = expertService.addSubService(expert.getId(),subService.getId());
//        expert = expertService.addSubService(expert.getId(),subService2.getId());
//
//        assertThat(orderService.findOrdersForExpert(expert.getId()).size()).isGreaterThan(1);
    }

    @Test
    void orderStatusChangeAfterOneOffer() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.OrderNotFoundException {
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
        order = orderService.findById(order.getId());
        assertThat(order.getOrderState()).isEqualTo(OrderState.WAITING_TO_SELECT_SUGGESTION);
    }

    @Test
    void expertShouldGetAddedToOrder() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.OrderNotFoundException, CustomExceptions.ExpertHasNoOfferForOfferException, CustomExceptions.ExpertNotFoundException, CustomExceptions.OfferNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("ihopePas213@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);

        SubService subService = SubService.builder()
                .name("pleaseAddex123123pert")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("ihopePas243@gmaill.com")
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

        order = orderService.choseOffer(offer.getId(),order.getId());
        assertThat(order.getSelectedOffer()).isNotNull();
    }

    @Test
    void expertWithNoOfferShouldNotGetAdded() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.OrderNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("onelasttesth@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);

        SubService subService = SubService.builder()
                .name("onelasttesth2")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("onelasttesth6@gmaill.com")
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

        Order order2 = Order.builder()
                .orderState(OrderState.WAITING_FOR_SUGGESTIONS)
                .address("Some aasdadddress")
                .subService(subService)
                .customer(customer)
                .suggestedPrice(200.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        orderService.register(order2);

        Offer offer = Offer.builder()
                .order(order)
                .suggestedPrice(200.0)
                .expert(expert)
                .startingDate(LocalDateTime.now().plusDays(1))
                .completionDate(LocalDateTime.now().plusDays(3))
                .build();

        offer = offerService.register(offer);

        Offer offer2 = Offer.builder()
                .order(order2)
                .suggestedPrice(200.0)
                .expert(expert)
                .startingDate(LocalDateTime.now().plusDays(1))
                .completionDate(LocalDateTime.now().plusDays(3))
                .build();

        offer = offerService.register(offer2);

        Expert finalExpert = expert;
        Offer finalOffer = offer;
        assertThatThrownBy(() -> orderService.choseOffer(finalOffer.getId(),order2.getId())).isInstanceOf(CustomExceptions.ExpertHasNoOfferForOfferException.class);
    }

    @Test
    void orderStateShouldChangeFromStartedToFinished() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.NotTheCorrectTimeToChangeStatusException, CustomExceptions.OrderNotFoundException {

        SubService subService = SubService.builder()
                .name("statechangetofinish")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("statechangetofinish1@gmaill.com")
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
                .orderState(OrderState.STARTED)
                .suggestedPrice(200.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        order = orderService.register(order);
        order = orderService.statusToFinished(order.getId());
        assertThat(order.getOrderState()).isEqualTo(OrderState.FINISHED);
    }

    @Test
    void notStartedOrdersShouldNotFinish() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException {

        SubService subService = SubService.builder()
                .name("thisDoesntend")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("thisDoesntend@gmaill.com")
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
                .orderState(OrderState.WAITING_FOR_EXPERT)
                .suggestedPrice(200.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        order = orderService.register(order);
        Order finalOrder = order;
        assertThatThrownBy( () -> orderService.statusToFinished(finalOrder.getId())).isInstanceOf(CustomExceptions.NotTheCorrectTimeToChangeStatusException.class);
    }

    @Test
    void orderStateShouldNotChangeFromWaitingToStartedBeforeCorrectTime() throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.OrderNotFoundException, CustomExceptions.ExpertHasNoOfferForOfferException, CustomExceptions.ExpertNotFoundException, CustomExceptions.OfferNotFoundException {

        SubService subService = SubService.builder()
                .name("theginalginaltests")
                .basePrice(100.0)
                .build();

        subServiceService.register(subService);

        User user2 = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("askdjh@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user2)
                .build();

        expert = expertService.register(expert);

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("asdasd234@gmaill.com")
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
                .orderState(OrderState.WAITING_FOR_EXPERT)
                .suggestedPrice(200.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .build();

        order = orderService.register(order);

        Offer offer = Offer.builder()
                .expert(expert)
                .order(order)
                .suggestedPrice(200.0)
                .startingDate(LocalDateTime.now().plusDays(1))
                .completionDate(LocalDateTime.now().plusDays(3))
                .build();

        offer = offerService.register(offer);

        order = orderService.findById(order.getId());
        order = orderService.choseOffer(offer.getId(),order.getId());
        Order finalOrder = order;
        assertThatThrownBy( () ->  orderService.statusToStarted(finalOrder.getId())).isInstanceOf(CustomExceptions.NotTheCorrectTimeToChangeStatusException.class);
    }
}