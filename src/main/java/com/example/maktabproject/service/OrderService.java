package com.example.maktabproject.service;

import com.example.maktabproject.model.dto.user.UserOrderFilterRequestDto;
import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    Order register(Order order) throws InvalidPriceException, InvalidTimeException;

    void delete(Order order);

    Order findById(Long id) throws OrderNotFoundException;

    List<Order> findAll();

    List<Order> findOrdersForExpert(Long expertId) throws ExpertNotFoundException;

    Order choseOffer(Long offerId, Long orderId) throws OrderNotFoundException, InvalidPriceException, InvalidTimeException, ExpertHasNoOfferForOfferException, ExpertNotFoundException, OfferNotFoundException;

    boolean priceValidation(Order order);

    boolean dateValidation(LocalDateTime localDateTime);

    Order statusToStarted(Long orderId) throws InvalidPriceException, InvalidTimeException, NotTheCorrectTimeToChangeStatusException, OrderNotFoundException;

    Order statusToFinished(Long orderId) throws InvalidPriceException, InvalidTimeException, NotTheCorrectTimeToChangeStatusException, OrderNotFoundException;

    Order statusToWaitingToSelect(Long orderId) throws OrderNotFoundException, InvalidPriceException, InvalidTimeException;

    Order statusToPaid(Long orderId) throws OrderNotFoundException, InsufficientFundException, CustomerNotFoundException, ExpertNotFoundException, InvalidPriceException, InvalidTimeException;

    List<Order> filterOrderCustomer(Long customerId, UserOrderFilterRequestDto userOrderFilterRequestDto);

    List<Order> filterOrderExpert(Long expertId, UserOrderFilterRequestDto userOrderFilterRequestDto);
}
