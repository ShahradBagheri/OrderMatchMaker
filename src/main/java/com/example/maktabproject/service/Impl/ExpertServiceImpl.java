package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.enumeration.ExpertStatus;
import com.example.maktabproject.repository.ExpertRepository;
import com.example.maktabproject.service.ExpertService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;
    private final SubServiceServiceImpl subServiceService;

    @Override
    public Expert register(Expert expert) {

        try {
            if (expert.getScore() < 0)
                expert.setExpertStatus(ExpertStatus.INACTIVE);
            return expertRepository.save(expert);
        } catch (ConstraintViolationException | DataAccessException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Expert expert) {

        expertRepository.delete(expert);
    }

    @Override
    public Expert findById(Long id) throws ExpertNotFoundException {

        return expertRepository.findById(id).orElseThrow(
                ExpertNotFoundException::new
        );
    }

    @Override
    public List<Expert> findAll() {

        return expertRepository.findAll();
    }

    @Override
    public Expert findByUser(Long userId) throws ExpertNotFoundException {

        return expertRepository.findByUser_Id(userId).orElseThrow(
                ExpertNotFoundException::new
        );
    }

    @Override
    public Expert changePassword(Long expertId, String password) throws ExpertNotFoundException {

        Expert expert = findById(expertId);
        expert.getUser().setPassword(password);
        return register(expert);
    }

}
