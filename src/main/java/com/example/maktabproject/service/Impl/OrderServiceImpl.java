package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.OrderNotFoundException;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.repository.OrderRepository;
import com.example.maktabproject.service.OrderService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order register(Order order) {
        try{
            return orderRepository.save(order);
        } catch (ConstraintViolationException | DataAccessException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Order order) {

        orderRepository.delete(order);
    }

    @Override
    public Order findById(Long id) throws OrderNotFoundException {

        return orderRepository.findById(id).orElseThrow(
            OrderNotFoundException::new
        );
    }

    @Override
    public List<Order> findAll() {

        return orderRepository.findAll();
    }

    @Override
    public boolean priceValidation(Order order) {

        if(order.getSuggestedPrice() != null && order.getSubService() != null)
            return order.getSuggestedPrice() < order.getSubService().getBasePrice();
        return false;
    }
}
