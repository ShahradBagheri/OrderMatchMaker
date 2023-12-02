package com.example.maktabproject.service.Impl;

import com.example.maktabproject.dto.order.OrderFilterCriteriaDto;
import com.example.maktabproject.dto.user.UserFilterCriteriaDto;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.enumeration.ExpertStatus;
import com.example.maktabproject.model.enumeration.OrderState;
import com.example.maktabproject.repository.ExpertRepository;
import com.example.maktabproject.repository.OrderRepository;
import com.example.maktabproject.repository.UserRepository;
import com.example.maktabproject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final SubServiceServiceImpl subServiceService;
    private final ExpertServiceImpl expertService;
    private final ExpertRepository expertRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CustomerServiceImpl customerService;

    @Override
    public Expert addExpertSubService(Long expertId, Long subServiceId) {

        Expert expert = expertService.findById(expertId);
        SubService subService = subServiceService.findById(subServiceId);

        expert.getSubServices().add(subService);

        return expertService.register(expert);
    }

    @Override
    public Expert removeExpertSubService(Long expertId, Long subServiceId) {

        Expert expert = expertService.findById(expertId);
        SubService subService = subServiceService.findById(subServiceId);

        expert.getSubServices().remove(subService);

        return expertService.register(expert);
    }


    @Override
    public Expert updateExpertStatus(Long expertId, ExpertStatus expertStatus) {

        Expert expert = expertService.findById(expertId);
        expert.setExpertStatus(expertStatus);
        return expertService.register(expert);
    }

    @Override
    public SubService editSubServicePrice(Long subServiceId, Double newBasePrice) {

        SubService subService = subServiceService.findById(subServiceId);
        subService.setBasePrice(newBasePrice);

        return subServiceService.register(subService);
    }

    @Override
    public SubService editSubServiceComment(Long subServiceId, String newComment) {

        SubService subService = subServiceService.findById(subServiceId);
        subService.setComment(newComment);

        return subServiceService.register(subService);
    }

    @Override
    public List<User> filterUsers(UserFilterCriteriaDto userFilterCriteriaDto) {

        Specification<Expert> expertSpecification = Specification.where(null);
        Specification<User> userSpecification = Specification.where(null);

        if (userFilterCriteriaDto.scoreHigher() != null)
            expertSpecification = expertSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("score"), userFilterCriteriaDto.scoreHigher()));

        if (userFilterCriteriaDto.scoreLower() != null)
            expertSpecification = expertSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("score"), userFilterCriteriaDto.scoreLower()));

        if (userFilterCriteriaDto.subService() != null)
            expertSpecification = expertSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.isMember(userFilterCriteriaDto.subService(), root.get("subServices")));

        if (userFilterCriteriaDto.firstname() != null)
            userSpecification = userSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("firstname"), "%" + userFilterCriteriaDto.firstname() + "%"));

        if (userFilterCriteriaDto.lastname() != null)
            userSpecification = userSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("lastname"), "%" + userFilterCriteriaDto.lastname() + "%"));

        if (userFilterCriteriaDto.email() != null)
            userSpecification = userSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), "%" + userFilterCriteriaDto.email() + "%"));

        if (userFilterCriteriaDto.afterCreationDate() != null)
            userSpecification = userSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("registrationDate"), userFilterCriteriaDto.afterCreationDate()));

        if (userFilterCriteriaDto.beforeCreationDate() != null)
            userSpecification = userSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("registrationDate"), userFilterCriteriaDto.beforeCreationDate()));

        if (userFilterCriteriaDto.role() != null)
            userSpecification = userSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("role"), userFilterCriteriaDto.role()));

        List<User> expertUsers = expertRepository.findAll(expertSpecification).stream().map(Expert::getUser).toList();
        List<User> allUsers = userRepository.findAll(userSpecification);
        List<User> commonElements = new ArrayList<>();

        if (userFilterCriteriaDto.scoreLower() != null || userFilterCriteriaDto.scoreHigher() != null || userFilterCriteriaDto.subService() != null) {
            for (User expert : expertUsers) {
                if (allUsers.contains(expert) && !commonElements.contains(expert))
                    commonElements.add(expert);
            }
            return commonElements;
        }

        return allUsers;
    }

    @Override
    public List<Order> filterOrders(OrderFilterCriteriaDto orderFilterCriteriaDto) {

        Specification<Order> orderSpecification = Specification.where(null);

        if (orderFilterCriteriaDto.customer() != null)
            orderSpecification = orderSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("customer"), orderFilterCriteriaDto.customer()));

        if (orderFilterCriteriaDto.expert() != null)
            orderSpecification = orderSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("selectedOffer").get("expert"), orderFilterCriteriaDto.expert()));

        if (orderFilterCriteriaDto.startAfter() != null)
            orderSpecification = orderSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("startingDate"), orderFilterCriteriaDto.startAfter()));

        if (orderFilterCriteriaDto.startBefore() != null)
            orderSpecification = orderSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("startingDate"), orderFilterCriteriaDto.startBefore()));

        if (orderFilterCriteriaDto.orderState() != null)
            orderSpecification = orderSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("orderState"), orderFilterCriteriaDto.orderState()));

        if (orderFilterCriteriaDto.mainService() != null)
            orderSpecification = orderSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("subService").get("mainService"), orderFilterCriteriaDto.mainService()));

        if (orderFilterCriteriaDto.subService() != null)
            orderSpecification = orderSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("subService"), orderFilterCriteriaDto.subService()));

        return orderRepository.findAll(orderSpecification);
    }

    @Override
    public Long customerOrderSubmits(Long customerId) {
        return orderRepository.countOrderByCustomer_Id(customerId);
    }

    @Override
    public Long expertOrdersFinished(Long expertId) {
        return orderRepository.countOrderBySelectedOffer_Expert_IdAndOrderStateOrOrderState(expertId, OrderState.FINISHED, OrderState.PAID);
    }

    @Override
    public LocalDateTime customerSignedUpTime(Long customerId) {
        return customerService.findById(customerId).getUser().getRegistrationDate();
    }

    @Override
    public LocalDateTime expertSignedUpTime(Long expertId) {
        return expertService.findById(expertId).getUser().getRegistrationDate();
    }
}
