package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.SubService;
import com.example.maktabproject.repository.SubServiceRepository;
import com.example.maktabproject.service.SubServiceService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubServiceServiceImpl implements SubServiceService {

    private final SubServiceRepository subServiceRepository;

    @Override
    public SubService register(SubService subService) {

        try{
            return subServiceRepository.save(subService);
        } catch (ConstraintViolationException | DataAccessException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(SubService subService) {

    }

    @Override
    public SubService findById(Long id) {
        return null;
    }

    @Override
    public List<SubService> findAll() {
        return null;
    }
}
