package com.example.maktabproject.service;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.Expert;

import java.util.List;

public interface ExpertService {

    Expert register(Expert expert);

    void delete(Expert expert);

    Expert findById(Long id) throws CustomExceptions.ExpertNotFoundException;

    Expert findByUsername(String username);

    List<Expert> findAll();

    Expert findByUser(Long userId) throws CustomExceptions.ExpertNotFoundException;

    Expert changePassword(Long expertId, String password) throws CustomExceptions.ExpertNotFoundException;
}
