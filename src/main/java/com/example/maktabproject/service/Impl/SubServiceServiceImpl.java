package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.MainService;
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

        subServiceRepository.delete(subService);
    }

    @Override
    public SubService findById(Long id) throws SubServiceNotFoundException {

        return subServiceRepository.findById(id).orElseThrow(
                SubServiceNotFoundException::new
        );
    }

    @Override
    public List<SubService> findAll() {

        return subServiceRepository.findAll();
    }

    @Override
    public SubService addMainService(SubService subService, MainService mainService) {
        subService.setMainService(mainService);
        return register(subService);
    }
}
