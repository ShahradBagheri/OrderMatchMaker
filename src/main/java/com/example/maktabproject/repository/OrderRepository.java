package com.example.maktabproject.repository;

import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.enumeration.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findBySubServiceInAndOrderStateOrOrderState(List<SubService> subServices, OrderState orderState1, OrderState orderState2);
}
