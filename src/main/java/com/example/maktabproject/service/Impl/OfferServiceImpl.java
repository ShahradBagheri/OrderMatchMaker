package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.model.enums.OrderState;
import com.example.maktabproject.model.view.OfferView;
import com.example.maktabproject.repository.OfferRepository;
import com.example.maktabproject.repository.OfferViewRepository;
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
    private final OfferViewRepository offerViewRepository;

    @Override
    public Offer register(Offer offer) {

        if (!offer.getExpert().getSubServices().contains(offer.getOrder().getSubService()))
            throw new CustomExceptions.NotExpertsSubServiceException("this is not experts SubService!");

        if (offer.getOrder().getOffers().stream().map(Offer::getExpert).toList().contains(offer.getExpert()))
            throw new CustomExceptions.CanNotSendTwoOffersException("cant send two offer to the same order");

        if (!priceValidation(offer))
            throw new CustomExceptions.InvalidPriceException("invalid price!");

        if (!dateValidation(offer.getStartingDate()))
            throw new CustomExceptions.InvalidTimeException("invalid time!");


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
                () -> new CustomExceptions.OfferNotFoundException("offer not found!")
        );
    }

    @Override
    public List<Offer> findAll() {

        return offerRepository.findAll();
    }

    @Override
    @Transactional
    public List<Offer> findForCustomerPriceOrder(Long customerId) {

        Customer customer = customerService.findById(customerId);
        return offerRepository.findByOrder_CustomerAndOrder_orderStateOrderBySuggestedPrice(customer,OrderState.WAITING_TO_SELECT_SUGGESTION);
    }

    @Override
    @Transactional
    public List<Offer> findForCustomerScoreOrder(Long customerId) {

        Customer customer = customerService.findById(customerId);
        return offerRepository.findByOrder_CustomerAndOrder_orderStateOrderByExpert_Score(customer,OrderState.WAITING_TO_SELECT_SUGGESTION);
    }

    @Override
    public boolean priceValidation(Offer offer) {

        return offer.getSuggestedPrice() >= offer.getOrder().getSubService().getBasePrice();
    }

    @Override
    public boolean dateValidation(LocalDateTime localDateTime) {
        return localDateTime.isAfter(LocalDateTime.now());
    }

    @Override
    public List<OfferView> findAllViewsForCustomerPriceOrder(Long customerId) {
        return offerViewRepository.findAllByCustomerIdAndOrderStateOrderByOfferSuggestedPrice(customerId,OrderState.WAITING_TO_SELECT_SUGGESTION);
    }

    @Override
    public List<OfferView> findAllViewsForCustomerScoreOrder(Long customerId) {
        return offerViewRepository.findAllByCustomerIdAndOrderStateOrderByScore(customerId,OrderState.WAITING_TO_SELECT_SUGGESTION);
    }
}
