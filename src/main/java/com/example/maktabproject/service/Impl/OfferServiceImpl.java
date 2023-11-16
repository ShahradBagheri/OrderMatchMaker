package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.model.enumeration.OrderState;
import com.example.maktabproject.repository.OfferRepository;
import com.example.maktabproject.service.CustomerService;
import com.example.maktabproject.service.OfferService;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final CustomerService customerService;
    private final OrderServiceImpl orderService;

    @Override
    public Offer register(Offer offer) {

        try {
            if (priceValidation(offer))
                if (dateValidation(offer.getStartingDate())) {

                    offer = offerRepository.save(offer);
                    if (offer.getOrder().getOrderState().equals(OrderState.WAITING_FOR_SUGGESTIONS))
                        orderService.statusToWaitingToSelect(offer.getOrder().getId());

                    return offer;
                } else
                    throw new InvalidTimeException("invalid time!");
            throw new InvalidPriceException("invalid price!");
        } catch (ConstraintViolationException | DataAccessException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Offer offer) {

        offerRepository.delete(offer);
    }

    @Override
    public Offer findById(Long id){

        return offerRepository.findById(id).orElseThrow(
                () -> new OfferNotFoundException("offer not found!")
        );
    }

    @Override
    public List<Offer> findAll() {

        return offerRepository.findAll();
    }

    @Override
    @Transactional
    public List<Offer> findByCustomerPriceOrder(Long customerId){

        Customer customer = customerService.findById(customerId);
        return offerRepository.findByOrder_CustomerOrderBySuggestedPrice(customer);
    }

    @Override
    @Transactional
    public List<Offer> findByCustomerScoreOrder(Long customerId){

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
