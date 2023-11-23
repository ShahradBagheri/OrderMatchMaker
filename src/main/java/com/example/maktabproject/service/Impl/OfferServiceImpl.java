package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.model.enumeration.OrderState;
import com.example.maktabproject.repository.OfferRepository;
import com.example.maktabproject.service.CustomerService;
import com.example.maktabproject.service.OfferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        if (!offer.getExpert().getSubServices().contains(offer.getOrder().getSubService()))
            throw new NotExpertsSubServiceException("this is not experts SubService!");

        if (offer.getOrder().getOffers().stream().map(Offer::getExpert).toList().contains(offer.getExpert()))
            throw new CanNotSendTwoOffersException("cant send two offer to the same order");

        if (!priceValidation(offer))
            throw new InvalidPriceException("invalid price!");

        if (!dateValidation(offer.getStartingDate()))
            throw new InvalidTimeException("invalid time!");


        offer = offerRepository.save(offer);
        if (offer.getOrder().getOrderState().equals(OrderState.WAITING_FOR_SUGGESTIONS))
            orderService.statusToWaitingToSelect(offer.getOrder().getId());

        return offer;
    }

    @Override
    public void delete(Offer offer) {

        offerRepository.delete(offer);
    }

    @Override
    public Offer findById(Long id) {

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
    public List<Offer> findByCustomerPriceOrder(Long customerId) {

        Customer customer = customerService.findById(customerId);
        return offerRepository.findByOrder_CustomerOrderBySuggestedPrice(customer);
    }

    @Override
    @Transactional
    public List<Offer> findByCustomerScoreOrder(Long customerId) {

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
