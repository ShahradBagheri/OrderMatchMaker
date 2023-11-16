package com.example.maktabproject.service;

import com.example.maktabproject.dto.CustomerOrderFilterRequestDto;
import com.example.maktabproject.dto.OrderFilterCriteriaDto;
import com.example.maktabproject.dto.OrderFilterRequestDto;
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

    List<Order> filterOrderCustomer(Long customerId, CustomerOrderFilterRequestDto customerOrderFilterRequestDto);

    List<Order> filterOrderExpert(Long expertId, CustomerOrderFilterRequestDto customerOrderFilterRequestDto);
}
