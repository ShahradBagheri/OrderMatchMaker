package com.example.maktabproject.service;

import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.exception.InvalidPriceException;
import com.example.maktabproject.exception.InvalidTimeException;
import com.example.maktabproject.exception.OfferNotFoundException;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Offer;

import java.time.LocalDateTime;
import java.util.List;

public interface OfferService {

    Offer register(Offer offer) throws InvalidTimeException, InvalidPriceException;

    void delete(Offer offer);

    Offer findById(Long id) throws OfferNotFoundException;

    List<Offer> findAll();

    List<Offer> findByCustomerPriceOrder(Long customerId) throws CustomerNotFoundException;

    List<Offer> findByCustomerScoreOrder(Long customerId) throws CustomerNotFoundException;

    boolean priceValidation(Offer offer);

    boolean dateValidation(LocalDateTime localDateTime);
}
