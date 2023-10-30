package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.enumeration.ExpertStatus;
import com.example.maktabproject.service.AdminService;
import com.example.maktabproject.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final SubServiceServiceImpl subServiceService;
    private final ExpertServiceImpl expertService;

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
}
