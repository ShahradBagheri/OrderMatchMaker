package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.MainService;
import com.example.maktabproject.repository.MainServiceRepository;
import com.example.maktabproject.service.MainServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceServiceImpl implements MainServiceService {

    private final MainServiceRepository mainServiceRepository;

    @Override
    public MainService create(MainService mainService) {
        return null;
    }

    @Override
    public MainService update(MainService mainService) {
        return null;
    }

    @Override
    public void delete(MainService mainService) {

    }

    @Override
    public MainService findById(Long id) {
        return null;
    }

    @Override
    public List<MainService> findAll() {
        return null;
    }
}
