package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.enumeration.ExpertStatus;
import com.example.maktabproject.repository.ExpertRepository;
import com.example.maktabproject.service.ExpertService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;
    private final SubServiceServiceImpl subServiceService;

    @Override
    public Expert register(Expert expert) {

        try{
            return expertRepository.save(expert);
        } catch (ConstraintViolationException | DataAccessException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Expert expert) {

        expertRepository.delete(expert);
    }

    @Override
    public Expert findById(Long id) throws ExpertNotFoundException {

        return expertRepository.findById(id).orElseThrow(
                ExpertNotFoundException::new
        );
    }

    @Override
    public List<Expert> findAll() {

        return expertRepository.findAll();
    }

    @Override
    public Expert findByUser(User user) throws ExpertNotFoundException {

        return expertRepository.findByUser(user).orElseThrow(
                ExpertNotFoundException::new
        );
    }

    @Override
    public Expert changePassword(Expert expert, String password) throws ExpertNotFoundException {

        Expert findExpert = findById(expert.getId());
        findExpert.getUser().setPassword(password);
        return register(findExpert);
    }

    @Override
    public Expert updateStatus(Expert expert, ExpertStatus expertStatus) throws ExpertNotFoundException {

        Expert findExpert = findById(expert.getId());
        findExpert.setExpertStatus(expertStatus);
        return register(findExpert);
    }

    @Override
    public Expert addSubService(Expert expert, SubService subService) throws ExpertNotFoundException, SubServiceNotFoundException {

        expert = findById(expert.getId());
        subService = subServiceService.findById(subService.getId());

        if (expert.getSubServices() == null){
            List<SubService> subServices = List.of(subService);
            expert.setSubServices(subServices);
        }else
            expert.getSubServices().add(subService);

        return register(expert);
    }
}
