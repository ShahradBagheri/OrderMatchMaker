package com.example.maktabproject.service;

import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.Expert;

public interface AdminService {

    Expert addExpertSubService(Long expertId, Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException;

    Expert removeExpertSubService(Long expertId, Long subServiceId) throws ExpertNotFoundException, SubServiceNotFoundException;
}
