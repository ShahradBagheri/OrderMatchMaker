package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.MainServiceNotFoundException;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.repository.MainServiceRepository;
import com.example.maktabproject.service.MainServiceService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainServiceServiceImpl implements MainServiceService {

    private final MainServiceRepository mainServiceRepository;

    @Override
    public MainService register(MainService mainService) {

        try {
            return mainServiceRepository.save(mainService);
        } catch (ConstraintViolationException | DataAccessException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(MainService mainService) {

        mainServiceRepository.delete(mainService);
    }

    @Override
    public MainService findById(Long id) {

        return mainServiceRepository.findById(id).orElseThrow(
                () -> new MainServiceNotFoundException("Main service not found")
        );
    }

    @Override
    public List<MainService> findAll() {

        return mainServiceRepository.findAll();
    }
}
