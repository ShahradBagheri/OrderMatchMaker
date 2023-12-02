package com.example.maktabproject.service;

import com.example.maktabproject.dto.order.OrderFilterCriteriaDto;
import com.example.maktabproject.dto.user.UserFilterCriteriaDto;
import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Order;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.enumeration.ExpertStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminService {

    Expert addExpertSubService(Long expertId, Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException;

    Expert removeExpertSubService(Long expertId, Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException;

    Expert updateExpertStatus(Long expertId, ExpertStatus expertStatus) throws ExpertNotFoundException;

    SubService editSubServicePrice(Long subServiceId, Double newBasePrice) throws SubServiceNotFoundException;

    SubService editSubServiceComment(Long subServiceId, String newComment) throws SubServiceNotFoundException;

    List<User> filterUsers(UserFilterCriteriaDto userFilterCriteriaDto);

    List<Order> filterOrders(OrderFilterCriteriaDto orderFilterCriteriaDto);

    Long customerOrderSubmits(Long customerId);

    Long expertOrdersFinished(Long expertId);

    LocalDateTime customerSignedUpTime(Long customerId);

    LocalDateTime expertSignedUpTime(Long expertId);
}
