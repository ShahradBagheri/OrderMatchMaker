package com.example.maktabproject.service.Impl;

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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;
    private final SubServiceServiceImpl subServiceService;

    @Override
    public Expert register(Expert expert) {

        try {
            return expertRepository.save(expert);
        } catch (ConstraintViolationException | DataAccessException e) {
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
    public Expert findByUser(Long userId) throws ExpertNotFoundException {

        return expertRepository.findByUser_Id(userId).orElseThrow(
                ExpertNotFoundException::new
        );
    }

    @Override
    public Expert changePassword(Long expertId, String password) throws ExpertNotFoundException {

        Expert expert = findById(expertId);
        expert.getUser().setPassword(password);
        return register(expert);
    }

    @Override
    public Expert updateStatus(Long expertId, ExpertStatus expertStatus) throws ExpertNotFoundException {

        Expert expert = findById(expertId);
        expert.setExpertStatus(expertStatus);
        return register(expert);
    }

    @Override
    public Expert addSubService(Long expertId, SubService subService) throws ExpertNotFoundException, SubServiceNotFoundException {

        Expert expert = findById(expertId);
        subService = subServiceService.findById(subService.getId());

        expert.getSubServices().add(subService);

        return register(expert);
    }
}
