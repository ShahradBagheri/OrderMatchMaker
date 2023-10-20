package com.example.maktabproject.repository;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByOrder_CustomerOrderBySuggestedPrice(Customer customer);

    List<Offer> findByOrder_CustomerOrderByExpert_Score(Customer customer);

    Offer findByExpertAndOrder(Expert expert, Order order);
}
