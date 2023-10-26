package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.enumeration.OrderState;
import com.example.maktabproject.repository.OfferRepository;
import com.example.maktabproject.repository.OrderRepository;
import com.example.maktabproject.service.ExpertService;
import com.example.maktabproject.service.OrderService;
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
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OfferRepository offerRepository;
    private final OfferServiceImpl offerService;
    private final ExpertService expertService;

    @Override
    public Order register(Order order) throws InvalidPriceException, InvalidTimeException {
        try{

            if(priceValidation(order))
                if(dateValidation(order.getStartingDate()))
                    return orderRepository.save(order);
                else
                    throw new InvalidTimeException();
            throw new InvalidPriceException();

        } catch (ConstraintViolationException | DataAccessException e){
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Order order) {

        orderRepository.delete(order);
    }

    @Override
    public Order findById(Long id) throws OrderNotFoundException {

        return orderRepository.findById(id).orElseThrow(
            OrderNotFoundException::new
        );
    }

    @Override
    public List<Order> findAll() {

        return orderRepository.findAll();
    }

    @Override
    public List<Order> findOrdersForExpert(Long expertId) throws ExpertNotFoundException {

        Expert expert = expertService.findById(expertId);
        return orderRepository.findBySubServiceInAndOrderStateOrOrderState(expert.getSubServices(), OrderState.WAITING_FOR_SUGGESTIONS,OrderState.WAITING_TO_SELECT_SUGGESTION);
    }

    @Override
    public Order choseOffer(Long offerId, Long orderId) throws OrderNotFoundException, InvalidPriceException, InvalidTimeException, ExpertHasNoOfferForOfferException, OfferNotFoundException {

        Offer offer = offerService.findById(offerId);

        Order order = findById(orderId);

        if (order.getOffers().contains(offer)) {
            order.setOrderState(OrderState.WAITING_FOR_EXPERT);
            order.setSelectedOffer(offer);
            return register(order);
        }
        throw new  ExpertHasNoOfferForOfferException();
    }

    @Override
    public boolean priceValidation(Order order) {

        if(order.getSuggestedPrice() != null && order.getSubService() != null)
            return order.getSuggestedPrice() > order.getSubService().getBasePrice();
        return false;
    }

    @Override
    public boolean dateValidation(LocalDateTime localDateTime) {
        return localDateTime.isAfter(LocalDateTime.now());
    }

    @Override
    public Order statusToStarted(Long orderId) throws InvalidPriceException, InvalidTimeException, NotTheCorrectTimeToChangeStatusException, OrderNotFoundException {

        Order order = findById(orderId);
        if(LocalDateTime.now().isAfter(offerRepository.findByExpertAndOrder(order.getSelectedOffer().getExpert(), order).getStartingDate())){
            order.setOrderState(OrderState.STARTED);
            return register(order);
        }
        throw new NotTheCorrectTimeToChangeStatusException();
    }

    @Override
    public Order statusToFinished(Long orderId) throws InvalidPriceException, InvalidTimeException, NotTheCorrectTimeToChangeStatusException, OrderNotFoundException {

        Order order = findById(orderId);
        if (order.getOrderState() == OrderState.STARTED) {
            order.setOrderState(OrderState.FINISHED);
            return register(order);
        }
        throw new NotTheCorrectTimeToChangeStatusException();
    }

    @Override
    public Order statusToWaitingToSelect(Long orderId) throws OrderNotFoundException, InvalidPriceException, InvalidTimeException {

        Order order = findById(orderId);
        order.setOrderState(OrderState.WAITING_TO_SELECT_SUGGESTION);
        return register(order);
    }
}
