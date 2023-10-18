package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import com.example.maktabproject.repository.ExpertRepository;
import com.example.maktabproject.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;

    @Override
    public Expert register(Expert expert) {
        return null;
    }

    @Override
    public void delete(Expert expert) {

    }

    @Override
    public Expert findById(Long id) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public List<Expert> findAll() {
        return null;
    }

    @Override
    public Expert findByUser(User user) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public Expert changePassword(Expert expert, String password) {
        return null;
    }
}
