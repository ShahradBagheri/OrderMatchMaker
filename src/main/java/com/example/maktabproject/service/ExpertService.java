package com.example.maktabproject.service;

import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;

import java.util.List;

public interface ExpertService {

    Expert register(Expert expert);

    void delete(Expert expert);

    Expert findById(Long id) throws CustomerNotFoundException;

    List<Expert> findAll();

    Expert findByUser(User user) throws CustomerNotFoundException;

    Expert changePassword(Expert expert,String password);
}
