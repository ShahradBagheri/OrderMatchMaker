package com.example.maktabproject.service;

import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.User;
import com.example.maktabproject.model.enumeration.ExpertStatus;

import java.util.List;

public interface ExpertService {

    Expert register(Expert expert);

    void delete(Expert expert);

    Expert findById(Long id) throws ExpertNotFoundException;

    List<Expert> findAll();

    Expert findByUser(User user) throws ExpertNotFoundException;

    Expert changePassword(Expert expert,String password) throws ExpertNotFoundException;

    Expert updateStatus(Expert expert, ExpertStatus expertStatus) throws ExpertNotFoundException;

    Expert addSubService(Expert expert, SubService subService) throws ExpertNotFoundException, SubServiceNotFoundException;
}
