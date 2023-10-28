package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final SubServiceServiceImpl subServiceService;
    private final ExpertServiceImpl expertService;

    @Override
    public Expert addSubService(Long expertId, Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException {

        Expert expert = expertService.findById(expertId);
        SubService subService = subServiceService.findById(subServiceId);

        expert.getSubServices().add(subService);

        return expertService.register(expert);
    }

    @Override
    public Expert removeSubService(Long expertId, Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException {

        Expert expert = expertService.findById(expertId);
        SubService subService = subServiceService.findById(subServiceId);

        expert.getSubServices().remove(subService);

        return expertService.register(expert);
    }
}
