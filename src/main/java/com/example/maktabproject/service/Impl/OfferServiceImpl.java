package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.Offer;
import com.example.maktabproject.repository.OfferRepository;
import com.example.maktabproject.service.OfferService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public Offer register(Offer offer) {

        try{
            return offerRepository.save(offer);
        } catch (ConstraintViolationException | DataAccessException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Offer offer) {

    }

    @Override
    public Offer findById(Long id) {
        return null;
    }

    @Override
    public List<Offer> findAll() {
        return null;
    }
}
