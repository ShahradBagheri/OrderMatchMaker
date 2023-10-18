package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import com.example.maktabproject.repository.ExpertRepository;
import com.example.maktabproject.service.ExpertService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;

    @Override
    public Expert register(Expert expert) {

        try{
            return expertRepository.save(expert);
        } catch (ConstraintViolationException | DataAccessException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Expert expert) {

        expertRepository.delete(expert);
    }

    @Override
    public Expert findById(Long id) throws CustomerNotFoundException {

        return expertRepository.findById(id).orElseThrow(
                CustomerNotFoundException::new
        );
    }

    @Override
    public List<Expert> findAll() {

        return expertRepository.findAll();
    }

    @Override
    public Expert findByUser(User user) throws CustomerNotFoundException {

        return expertRepository.findByUser(user).orElseThrow(
                CustomerNotFoundException::new
        );
    }

    @Override
    public Expert changePassword(Expert expert, String password) {

        Expert findExpert = expertRepository.findById(expert.getId()).orElseThrow();
        findExpert.getUser().setPassword(password);
        return register(findExpert);
    }
}
