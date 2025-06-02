package com.example.maktabproject.repository;

import com.example.maktabproject.model.enums.OrderState;
import com.example.maktabproject.model.view.OrderView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderViewRepository extends JpaRepository<OrderView, Long> {

    List<OrderView> findAll(Specification<OrderView> specification);

    List<OrderView> findAllByCustomerId(Long customerId);

    List<OrderView> findAllByCustomerIdAndOrderState(Long customerId, OrderState orderState);

    List<OrderView> findAllByExpertIdAndOrderState(Long expertId, OrderState orderStateL);

    List<OrderView> findAllByExpertId(Long expertId);

    List<OrderView> findBySubServiceIdInAndOrderStateOrOrderState(List<Long> subServices, OrderState orderState1, OrderState orderState2);
}
