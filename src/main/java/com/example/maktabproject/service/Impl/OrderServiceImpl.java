package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Customer;
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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OfferRepository offerRepository;
    private final ExpertService expertService;
    private final CustomerServiceImpl customerService;

    @Override
    public Order register(Order order) throws InvalidPriceException, InvalidTimeException {
        try {

            if (order.getOrderState() == OrderState.WAITING_FOR_SUGGESTIONS) {
                if (priceValidation(order))
                    if (dateValidation(order.getStartingDate()))
                        return orderRepository.save(order);
                    else
                        throw new InvalidTimeException();
                throw new InvalidPriceException();
            } else
                return orderRepository.save(order);

        } catch (ConstraintViolationException | DataAccessException e) {
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
        return orderRepository.findBySubServiceInAndOrderStateOrOrderState(expert.getSubServices(), OrderState.WAITING_FOR_SUGGESTIONS, OrderState.WAITING_TO_SELECT_SUGGESTION);
    }

    @Override
    public Order choseOffer(Long offerId, Long orderId) throws OrderNotFoundException, InvalidPriceException, InvalidTimeException, ExpertHasNoOfferForOfferException, OfferNotFoundException {

        Offer offer = offerRepository.findById(offerId).orElseThrow(
                OfferNotFoundException::new
        );

        Order order = findById(orderId);

        if (order.getOffers().stream().map(Offer::getId).toList().contains(offerId)) {
            order.setOrderState(OrderState.WAITING_FOR_EXPERT);
            order.setSelectedOffer(offer);
            return register(order);
        }
        throw new ExpertHasNoOfferForOfferException();
    }

    @Override
    public boolean priceValidation(Order order) {

        if (order.getSuggestedPrice() != null && order.getSubService() != null)
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
        if (LocalDateTime.now().isAfter(offerRepository.findByExpertAndOrder(order.getSelectedOffer().getExpert(), order).getStartingDate())) {
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
            if (LocalDateTime.now().isAfter(order.getSelectedOffer().getCompletionDate())) {

                Duration duration = Duration.between(LocalDateTime.now(), order.getSelectedOffer().getCompletionDate());
                long hoursDifference = duration.toHours();

                Expert expert = order.getSelectedOffer().getExpert();
                expert.setScore(expert.getScore() - Math.abs(hoursDifference));
                expertService.register(expert);
            }
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

    @Override
    public Order statusToPaid(Long orderId) throws OrderNotFoundException, InsufficientFundException, CustomerNotFoundException, ExpertNotFoundException, InvalidPriceException, InvalidTimeException {

        Order order = findById(orderId);
        Customer customer = customerService.findById(order.getCustomer().getId());
        Expert expert = expertService.findById(order.getSelectedOffer().getExpert().getId());
        double customerBalance = customer.getUser().getWallet().getCredit();
        double expertBalance = expert.getUser().getWallet().getCredit();

        if (order.getSelectedOffer().getSuggestedPrice() > customerBalance)
            throw new InsufficientFundException("not enough money");

        customer.getUser().getWallet().setCredit(customerBalance - order.getSelectedOffer().getSuggestedPrice());
        expert.getUser().getWallet().setCredit(expertBalance + order.getSelectedOffer().getSuggestedPrice() * 70 / 100);

        customerService.register(customer);
        expertService.register(expert);
        order.setOrderState(OrderState.PAID);
        return register(order);
    }
}
