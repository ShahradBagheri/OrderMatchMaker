package com.example.maktabproject.service;

import com.example.maktabproject.exception.MainServiceNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.exception.SubServiceTwoMainServiceException;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.SubService;

import java.util.List;

public interface SubServiceService {

    SubService register(SubService subService);

    void delete(SubService subService);

    SubService findById(Long id) throws SubServiceNotFoundException;

    List<SubService> findAll();

    SubService addMainService(Long subServiceId, Long mainServiceId) throws SubServiceNotFoundException, SubServiceTwoMainServiceException, MainServiceNotFoundException;

    SubService removeMainService(Long subServiceId) throws SubServiceNotFoundException, MainServiceNotFoundException;

    List<SubService> findByMainService(Long mainServiceId) throws MainServiceNotFoundException;

    SubService editPrice(Long subServiceId, Double newBasePrice) throws SubServiceNotFoundException;

    SubService editComment(Long subServiceId, String newComment) throws SubServiceNotFoundException;
}
