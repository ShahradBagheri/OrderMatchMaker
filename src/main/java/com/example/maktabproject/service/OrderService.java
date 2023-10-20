package com.example.maktabproject.service;

import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    Order register(Order order) throws InvalidPriceException, InvalidTimeException;

    void delete(Order order);

    Order findById(Long id) throws OrderNotFoundException;

    List<Order> findAll();

    List<Order> findOrdersForExpert(Expert expert);

    Order choseExpert(Expert expert, Order order) throws OrderNotFoundException, InvalidPriceException, InvalidTimeException, ExpertHasNoOfferForOfferException;

    boolean priceValidation(Order order);

    boolean dateValidation(LocalDateTime localDateTime);

    Order updateOrderStatus(Order order) throws OrderNotFoundException, InvalidPriceException, InvalidTimeException;

    Order statusToStarted(Order order) throws InvalidPriceException, InvalidTimeException, NotTheCorrectTimeToChangeStatusException;

    Order statusToFinished(Order order) throws InvalidPriceException, InvalidTimeException, NotTheCorrectTimeToChangeStatusException;
}
