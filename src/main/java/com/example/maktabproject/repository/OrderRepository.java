package com.example.maktabproject.repository;

import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.enumeration.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    List<Order> findBySubServiceInAndOrderStateOrOrderState(List<SubService> subServices, OrderState orderState1, OrderState orderState2);

    Long countOrderByCustomer_Id(Long customerId);

    Long countOrderBySelectedOffer_Expert_IdAndOrderStateOrOrderState(Long expertId,OrderState orderState1, OrderState orderState2);

    List<Order> findAllByCustomer_IdAndOrderState(Long customerId, OrderState orderState);

    List<Order> findAllBySelectedOffer_Expert_IdAndOrderState(Long expertId, OrderState orderState);

    List<Order> findAllByCustomer_Id(Long customerId);

    List<Order> findAllBySelectedOffer_Expert_Id(Long expertId);
}
