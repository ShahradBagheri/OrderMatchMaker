package com.example.maktabproject.service;

import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.model.view.OfferView;

import java.time.LocalDateTime;
import java.util.List;

public interface OfferService {

    Offer register(Offer offer) throws CustomExceptions.InvalidTimeException, CustomExceptions.InvalidPriceException, CustomExceptions.OrderNotFoundException;

    void delete(Offer offer);

    Offer findById(Long id) throws CustomExceptions.OfferNotFoundException;

    List<Offer> findAll();

    List<Offer> findForCustomerPriceOrder(Long customerId) throws CustomExceptions.CustomerNotFoundException;

    List<Offer> findForCustomerScoreOrder(Long customerId) throws CustomExceptions.CustomerNotFoundException;

    boolean priceValidation(Offer offer);

    boolean dateValidation(LocalDateTime localDateTime);

    List<OfferView> findAllViewsForCustomerPriceOrder(Long customerId);

    List<OfferView> findAllViewsForCustomerScoreOrder(Long customerId);
}
