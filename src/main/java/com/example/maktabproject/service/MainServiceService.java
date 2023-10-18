package com.example.maktabproject.service;

import com.example.maktabproject.model.MainService;

import java.util.List;

public interface MainServiceService {

    MainService register(MainService mainService);

    void delete(MainService mainService);

    MainService findById(Long id);

    List<MainService> findAll();
}
