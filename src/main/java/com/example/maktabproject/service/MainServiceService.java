package com.example.maktabproject.service;

import com.example.maktabproject.exception.MainServiceNotFoundException;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.SubService;

import java.util.List;

public interface MainServiceService {

    MainService register(MainService mainService);

    void delete(MainService mainService);

    MainService findById(Long id) throws MainServiceNotFoundException;

    List<MainService> findAll();
}
