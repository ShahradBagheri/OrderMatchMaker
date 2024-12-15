package com.example.maktabproject.repository;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.enums.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByOrder_CustomerAndOrder_orderStateOrderBySuggestedPrice(Customer customer, OrderState orderState);

    List<Offer> findByOrder_CustomerAndOrder_orderStateOrderByExpert_Score(Customer customer, OrderState orderState);

    Offer findByExpertAndOrder(Expert expert, Order order);
}
