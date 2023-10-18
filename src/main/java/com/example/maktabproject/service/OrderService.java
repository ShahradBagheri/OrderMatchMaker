package com.example.maktabproject.service;

import com.example.maktabproject.model.Order;

import java.util.List;

public interface OrderService {

    Order register(Order order);

    void delete(Order order);

    Order findById(Long id);

    List<Order> findAll();
}
