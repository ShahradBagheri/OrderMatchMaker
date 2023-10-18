package com.example.maktabproject.repository;

import com.example.maktabproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
