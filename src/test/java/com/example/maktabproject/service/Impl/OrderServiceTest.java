package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.InvalidPriceException;
import com.example.maktabproject.exception.InvalidTimeException;
import com.example.maktabproject.exception.NotTheCorrectTimeToChangeStatusException;
import com.example.maktabproject.exception.OrderNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.enumeration.OrderState;
import com.example.maktabproject.repository.OfferRepository;
import com.example.maktabproject.repository.OrderRepository;
import com.example.maktabproject.service.ExpertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OfferRepository offerRepository;
    @Mock
    private ExpertService expertService;
    @Mock
    private CustomerServiceImpl customerService;

    private OrderServiceImpl orderService;

    @BeforeEach
    void setup(){
        orderService = new OrderServiceImpl(orderRepository,offerRepository,expertService,customerService);
    }

    @Test
    void orderStateShouldChangeWhenExpertComes() throws OrderNotFoundException, InvalidPriceException, InvalidTimeException, NotTheCorrectTimeToChangeStatusException {

        Order order = Order.builder()
                .selectedOffer(Offer.builder()
                        .expert(new Expert())
                        .startingDate(LocalDateTime.now().plusSeconds(300))
                        .build())
                .build();

        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Mockito.when(offerRepository.findByExpertAndOrder(any(Expert.class), any(Order.class))).thenReturn(Offer.builder()
                        .startingDate(LocalDateTime.now().minusDays(1))
                        .build());
        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        Order order1 = orderService.statusToStarted(1L);
        assertThat(order1).isNotNull();
    }
}
