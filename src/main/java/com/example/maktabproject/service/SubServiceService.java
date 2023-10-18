package com.example.maktabproject.service;

import com.example.maktabproject.model.SubService;

import java.util.List;

public interface SubServiceService {

    SubService register(SubService subService);

    void delete(SubService subService);

    SubService findById(Long id);

    List<SubService> findAll();
}
