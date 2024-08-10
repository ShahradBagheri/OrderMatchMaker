package com.example.maktabproject.service;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.dto.order.OrderFilterCriteriaDto;
import com.example.maktabproject.model.dto.user.UserFilterCriteriaDto;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.enums.ExpertStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminService {

    Expert addExpertSubService(Long expertId, Long subServiceId) throws CustomExceptions.ExpertNotFoundException, CustomExceptions.SubServiceNotFoundException;

    Expert removeExpertSubService(Long expertId, Long subServiceId) throws CustomExceptions.ExpertNotFoundException, CustomExceptions.SubServiceNotFoundException;

    Expert updateExpertStatus(Long expertId, ExpertStatus expertStatus) throws CustomExceptions.ExpertNotFoundException;

    SubService editSubServicePrice(Long subServiceId, Double newBasePrice) throws CustomExceptions.SubServiceNotFoundException;

    SubService editSubServiceComment(Long subServiceId, String newComment) throws CustomExceptions.SubServiceNotFoundException;

    List<User> filterUsers(UserFilterCriteriaDto userFilterCriteriaDto);

    List<Order> filterOrders(OrderFilterCriteriaDto orderFilterCriteriaDto);

    Long customerOrderSubmits(Long customerId);

    Long expertOrdersFinished(Long expertId);

    LocalDateTime customerSignedUpTime(Long customerId);

    LocalDateTime expertSignedUpTime(Long expertId);

    List<Expert> loadAWaitingConfirmationExperts();
}
