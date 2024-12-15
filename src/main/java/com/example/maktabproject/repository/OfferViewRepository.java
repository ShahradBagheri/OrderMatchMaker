package com.example.maktabproject.repository;

import com.example.maktabproject.model.enums.OrderState;
import com.example.maktabproject.model.view.OfferView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OfferViewRepository extends JpaRepository<OfferView, Long> {

    List<OfferView> findAllByCustomerIdAndOrderStateOrderByScore(Long customerId, OrderState orderState);

    List<OfferView> findAllByCustomerIdAndOrderStateOrderByOfferSuggestedPrice(Long customerId, OrderState orderState);

}
