package com.example.maktabproject.service.Impl;

import com.example.maktabproject.dto.UserFilterCriteriaDto;
import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.enumeration.ExpertStatus;
import com.example.maktabproject.repository.ExpertRepository;
import com.example.maktabproject.repository.UserRepository;
import com.example.maktabproject.service.AdminService;
import com.example.maktabproject.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final SubServiceServiceImpl subServiceService;
    private final ExpertServiceImpl expertService;
    private final ExpertRepository expertRepository;
    private final UserRepository userRepository;

    @Override
    public Expert addExpertSubService(Long expertId, Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException {

        Expert expert = expertService.findById(expertId);
        SubService subService = subServiceService.findById(subServiceId);

        expert.getSubServices().add(subService);

        return expertService.register(expert);
    }

    @Override
    public Expert removeExpertSubService(Long expertId, Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException {

        Expert expert = expertService.findById(expertId);
        SubService subService = subServiceService.findById(subServiceId);

        expert.getSubServices().remove(subService);

        return expertService.register(expert);
    }


    @Override
    public Expert updateExpertStatus(Long expertId, ExpertStatus expertStatus) throws ExpertNotFoundException {

        Expert expert = expertService.findById(expertId);
        expert.setExpertStatus(expertStatus);
        return expertService.register(expert);
    }

    @Override
    public SubService editSubServicePrice(Long subServiceId, Double newBasePrice) throws SubServiceNotFoundException {

        SubService subService = subServiceService.findById(subServiceId);
        subService.setBasePrice(newBasePrice);

        return subServiceService.register(subService);
    }

    @Override
    public SubService editSubServiceComment(Long subServiceId, String newComment) throws SubServiceNotFoundException {

        SubService subService = subServiceService.findById(subServiceId);
        subService.setComment(newComment);

        return subServiceService.register(subService);
    }

    @Override
    public List<User> filterUsers(UserFilterCriteriaDto userFilterCriteriaDto) {

        Specification<Expert> expertSpecification = Specification.where(null);
        Specification<User> userSpecification = Specification.where(null);

        if(userFilterCriteriaDto.score() != null)
            expertSpecification = expertSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("score"), userFilterCriteriaDto.score()));

        if(userFilterCriteriaDto.subService() != null)
            expertSpecification = expertSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.isMember(userFilterCriteriaDto.subService(),root.get("subServices")));

        if(userFilterCriteriaDto.firstname() != null)
            userSpecification = userSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("firstname"),"%" + userFilterCriteriaDto.firstname() + "%"));

        if(userFilterCriteriaDto.lastname() != null)
            userSpecification = userSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("lastname"),"%" + userFilterCriteriaDto.lastname() + "%"));

        if(userFilterCriteriaDto.email() != null)
            userSpecification = userSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"),"%" + userFilterCriteriaDto.email() + "%"));

        if(userFilterCriteriaDto.role() != null)
            userSpecification = userSpecification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("role"),userFilterCriteriaDto.role()));

        List<User> expertUsers = expertRepository.findAll(expertSpecification).stream().map(Expert::getUser).toList();
        List<User> allUsers = userRepository.findAll(userSpecification);
        List<User> commonElements = new ArrayList<>();

        if(userFilterCriteriaDto.score() != null || userFilterCriteriaDto.subService() != null){
            for (User expert : expertUsers) {
                if(allUsers.contains(expert) && !commonElements.contains(expert))
                    commonElements.add(expert);
            }
            return commonElements;
        }

        return allUsers;
    }
}
