package com.example.maktabproject.repository;

import com.example.maktabproject.model.view.OrderView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderViewRepository extends JpaRepository<OrderView, Long> {

    List<OrderView> findAll(Specification<OrderView> specification);
}
