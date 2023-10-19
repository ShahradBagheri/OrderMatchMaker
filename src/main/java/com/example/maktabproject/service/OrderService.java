package com.example.maktabproject.service;

import com.example.maktabproject.exception.OrderNotFoundException;
import com.example.maktabproject.model.Order;

import java.util.List;

public interface OrderService {

    Order register(Order order);

    void delete(Order order);

    Order findById(Long id) throws OrderNotFoundException;

    List<Order> findAll();

    boolean priceValidation(Order order);
}
