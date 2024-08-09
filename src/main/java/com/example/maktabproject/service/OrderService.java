package com.example.maktabproject.service;

import com.example.maktabproject.model.dto.user.UserOrderFilterRequestDto;
import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    Order register(Order order) throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException;

    void delete(Order order);

    Order findById(Long id) throws CustomExceptions.OrderNotFoundException;

    List<Order> findAll();

    List<Order> findOrdersForExpert(Long expertId) throws CustomExceptions.ExpertNotFoundException;

    Order choseOffer(Long offerId, Long orderId) throws CustomExceptions.OrderNotFoundException, CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.ExpertHasNoOfferForOfferException, CustomExceptions.ExpertNotFoundException, CustomExceptions.OfferNotFoundException;

    boolean priceValidation(Order order);

    boolean dateValidation(LocalDateTime localDateTime);

    Order statusToStarted(Long orderId) throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.NotTheCorrectTimeToChangeStatusException, CustomExceptions.OrderNotFoundException;

    Order statusToFinished(Long orderId) throws CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException, CustomExceptions.NotTheCorrectTimeToChangeStatusException, CustomExceptions.OrderNotFoundException;

    Order statusToWaitingToSelect(Long orderId) throws CustomExceptions.OrderNotFoundException, CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException;

    Order statusToPaid(Long orderId) throws CustomExceptions.OrderNotFoundException, CustomExceptions.InsufficientFundException, CustomExceptions.CustomerNotFoundException, CustomExceptions.ExpertNotFoundException, CustomExceptions.InvalidPriceException, CustomExceptions.InvalidTimeException;

    List<Order> filterOrderCustomer(Long customerId, UserOrderFilterRequestDto userOrderFilterRequestDto);

    List<Order> filterOrderExpert(Long expertId, UserOrderFilterRequestDto userOrderFilterRequestDto);
}
