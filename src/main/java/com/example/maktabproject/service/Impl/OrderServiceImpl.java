package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.dto.user.UserOrderFilterRequestDto;
import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.enums.OrderState;
import com.example.maktabproject.repository.OfferRepository;
import com.example.maktabproject.repository.OrderRepository;
import com.example.maktabproject.service.ExpertService;
import com.example.maktabproject.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @Transactional
    public Order register(Order order) {
        if (order.getOrderState() == OrderState.WAITING_FOR_SUGGESTIONS) {

            if (!priceValidation(order))
                throw new CustomExceptions.InvalidPriceException("invalid price!");

            if (!dateValidation(order.getStartingDate()))
                throw new CustomExceptions.InvalidTimeException("invalid time!");
        }
        return orderRepository.save(order);

    }

    @Override
    @Transactional
    public void delete(Order order) {

        orderRepository.delete(order);
    }

    @Override
    @Transactional
    public Order findById(Long id) {

        return orderRepository.findById(id).orElseThrow(
                () -> new CustomExceptions.OrderNotFoundException("order not found!")
        );
    }

    @Override
    @Transactional
    public List<Order> findAll() {

        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public List<Order> findOrdersForExpert(Long expertId) {

        Expert expert = expertService.findById(expertId);
        return orderRepository.findBySubServiceInAndOrderStateOrOrderState(expert.getSubServices(), OrderState.WAITING_FOR_SUGGESTIONS, OrderState.WAITING_TO_SELECT_SUGGESTION);
    }

    @Override
    @Transactional
    public Order choseOffer(Long offerId, Long orderId) {

        Offer offer = offerRepository.findById(offerId).orElseThrow(
                () -> new CustomExceptions.OfferNotFoundException("offer not found")
        );

        Order order = findById(orderId);

        if (order.getOffers().stream().map(Offer::getId).toList().contains(offerId)) {
            order.setOrderState(OrderState.WAITING_FOR_EXPERT);
            order.setSelectedOffer(offer);
            return register(order);
        }
        throw new CustomExceptions.ExpertHasNoOfferForOfferException("expert has no offer for offer");
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
    @Transactional
    public Order statusToStarted(Long orderId) {

        Order order = findById(orderId);
        if (LocalDateTime.now().isAfter(offerRepository.findByExpertAndOrder(order.getSelectedOffer().getExpert(), order).getStartingDate())) {
            order.setOrderState(OrderState.STARTED);
            return register(order);
        }
        throw new CustomExceptions.NotTheCorrectTimeToChangeStatusException("not the correct time to change status!");
    }

    @Override
    @Transactional
    public Order statusToFinished(Long orderId) {

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
        throw new CustomExceptions.NotTheCorrectTimeToChangeStatusException("not the correct time to change status!");
    }

    @Override
    @Transactional
    public Order statusToWaitingToSelect(Long orderId) {

        Order order = findById(orderId);
        order.setOrderState(OrderState.WAITING_TO_SELECT_SUGGESTION);
        return register(order);
    }

    @Override
    @Transactional
    public Order statusToPaid(Long orderId) {

        Order order = findById(orderId);
        Customer customer = customerService.findById(order.getCustomer().getId());
        Expert expert = expertService.findById(order.getSelectedOffer().getExpert().getId());
        double customerBalance = customer.getUser().getWallet().getCredit();
        double expertBalance = expert.getUser().getWallet().getCredit();

        if (order.getSelectedOffer().getSuggestedPrice() > customerBalance)
            throw new CustomExceptions.InsufficientFundException("not enough money");

        customer.getUser().getWallet().setCredit(customerBalance - order.getSelectedOffer().getSuggestedPrice());
        expert.getUser().getWallet().setCredit(expertBalance + order.getSelectedOffer().getSuggestedPrice() * 70 / 100);

        customerService.register(customer);
        expertService.register(expert);
        order.setOrderState(OrderState.PAID);
        return register(order);
    }

    @Override
    @Transactional
    public List<Order> filterOrderCustomer(Long customerId, UserOrderFilterRequestDto userOrderFilterRequestDto) {

        if (userOrderFilterRequestDto.orderState() == null)
            return orderRepository.findAllByCustomer_Id(customerId);

        return orderRepository.findAllByCustomer_IdAndOrderState(customerId, userOrderFilterRequestDto.orderState());
    }

    @Override
    @Transactional
    public List<Order> filterOrderExpert(Long expertId, UserOrderFilterRequestDto userOrderFilterRequestDto) {

        if (userOrderFilterRequestDto.orderState() == null)
            return orderRepository.findAllBySelectedOffer_Expert_Id(expertId);

        return orderRepository.findAllBySelectedOffer_Expert_IdAndOrderState(expertId, userOrderFilterRequestDto.orderState());
    }
}
