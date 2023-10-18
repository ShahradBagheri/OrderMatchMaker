package com.example.maktabproject.service;

import com.example.maktabproject.model.MainService;

import java.util.List;

public interface MainServiceService {

    MainService create(MainService mainService);

    MainService update(MainService mainService);

    void delete(MainService mainService);

    MainService findById(Long id);

    List<MainService> findAll();
}
