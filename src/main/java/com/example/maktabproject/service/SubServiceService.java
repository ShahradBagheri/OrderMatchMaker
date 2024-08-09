package com.example.maktabproject.service;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.SubService;

import java.util.List;

public interface SubServiceService {

    SubService register(SubService subService);

    void delete(SubService subService);

    SubService findById(Long id) throws CustomExceptions.SubServiceNotFoundException;

    List<SubService> findAll();

    SubService addMainService(Long subServiceId, Long mainServiceId) throws CustomExceptions.SubServiceNotFoundException, CustomExceptions.SubServiceTwoMainServiceException, CustomExceptions.MainServiceNotFoundException;

    SubService removeMainService(Long subServiceId) throws CustomExceptions.SubServiceNotFoundException, CustomExceptions.MainServiceNotFoundException;

    List<SubService> findByMainService(Long mainServiceId) throws CustomExceptions.MainServiceNotFoundException;
}
