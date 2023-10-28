package com.example.maktabproject.service;

import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.Expert;

public interface AdminService {

    Expert addSubService(Long expertId, Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException;

    Expert removeSubService(Long expertId, Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException;
}
