package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.Order;
import com.example.maktabproject.repository.OrderRepository;
import com.example.maktabproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order register(Order order) {
        return null;
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }
}
