package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.MainServiceNotFoundException;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.repository.MainServiceRepository;
import com.example.maktabproject.service.MainServiceService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceServiceImpl implements MainServiceService {

    private final MainServiceRepository mainServiceRepository;

    @Override
    public MainService register(MainService mainService) {

        try{
            return mainServiceRepository.save(mainService);
        } catch (ConstraintViolationException | DataAccessException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(MainService mainService) {

        mainServiceRepository.delete(mainService);
    }

    @Override
    public MainService findById(Long id) throws MainServiceNotFoundException {

        return mainServiceRepository.findById(id).orElseThrow(
                MainServiceNotFoundException::new
        );
    }

    @Override
    public List<MainService> findAll() {

        return mainServiceRepository.findAll();
    }
}
