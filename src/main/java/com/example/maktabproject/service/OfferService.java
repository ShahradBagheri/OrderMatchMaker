package com.example.maktabproject.service;

import com.example.maktabproject.model.Offer;

import java.util.List;

public interface OfferService {

    Offer register(Offer offer);

    void delete(Offer offer);

    Offer findById(Long id);

    List<Offer> findAll();
}
