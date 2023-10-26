package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.repository.CustomerRepository;
import com.example.maktabproject.repository.OfferRepository;
import com.example.maktabproject.service.CustomerService;
import com.example.maktabproject.service.OfferService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final CustomerService customerService;
    private final OrderServiceImpl orderService;

    @Override
    public Offer register(Offer offer) throws InvalidTimeException, InvalidPriceException, OrderNotFoundException {

        try{
            if(priceValidation(offer))
                if(dateValidation(offer.getStartingDate())){

                    if(offer.getOrder().getOffers().size() == 0)
                        orderService.statusToWaitingToSelect(offer.getOrder().getId());

                    return offerRepository.save(offer);
                    }
                else
                    throw new InvalidTimeException();
            throw new InvalidPriceException();
        } catch (ConstraintViolationException | DataAccessException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Offer offer) {

        offerRepository.delete(offer);
    }

    @Override
    public Offer findById(Long id) throws OfferNotFoundException {

        return offerRepository.findById(id).orElseThrow(
                OfferNotFoundException::new
        );
    }

    @Override
    public List<Offer> findAll() {

        return offerRepository.findAll();
    }

    @Override
    public List<Offer> findByCustomerPriceOrder(Long customerId) throws CustomerNotFoundException {

        Customer customer = customerService.findById(customerId);
        return offerRepository.findByOrder_CustomerOrderBySuggestedPrice(customer);
    }

    @Override
    public List<Offer> findByCustomerScoreOrder(Long customerId) throws CustomerNotFoundException {

        Customer customer = customerService.findById(customerId);
        return offerRepository.findByOrder_CustomerOrderByExpert_Score(customer);
    }

    @Override
    public boolean priceValidation(Offer offer) {

        return offer.getSuggestedPrice() >= offer.getOrder().getSubService().getBasePrice();
    }

    @Override
    public boolean dateValidation(LocalDateTime localDateTime) {
        return localDateTime.isAfter(LocalDateTime.now());
    }
}
