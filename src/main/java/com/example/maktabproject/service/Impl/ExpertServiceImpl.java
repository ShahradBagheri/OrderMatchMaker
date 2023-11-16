package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.enumeration.ExpertStatus;
import com.example.maktabproject.repository.ExpertRepository;
import com.example.maktabproject.service.ExpertService;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;
    private final SubServiceServiceImpl subServiceService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public Expert register(Expert expert) {

        try {

            if(expert.getId() == null){
                String password = expert.getUser().getPassword();
                expert.getUser().setPassword(bCryptPasswordEncoder.encode(password));
            }

            if (expert.getScore() < 0)
                expert.setExpertStatus(ExpertStatus.INACTIVE);
            return expertRepository.save(expert);
        } catch (ConstraintViolationException | DataAccessException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Expert expert) {

        expertRepository.delete(expert);
    }

    @Override
    @Transactional
    public Expert findById(Long id) throws ExpertNotFoundException {

        return expertRepository.findById(id).orElseThrow(
                () -> new ExpertNotFoundException("expert not found")
        );
    }

    @Override
    @Transactional
    public Expert findByUsername(String username) {
        return expertRepository.findByUser_Username(username).orElseThrow(
                () -> new ExpertNotFoundException("expert not found")
        );
    }

    @Override
    @Transactional
    public List<Expert> findAll() {

        return expertRepository.findAll();
    }

    @Override
    @Transactional
    public Expert findByUser(Long userId) throws ExpertNotFoundException {

        return expertRepository.findByUser_Id(userId).orElseThrow(
                () -> new ExpertNotFoundException("expert not found")
        );
    }

    @Override
    public Expert changePassword(Long expertId, String password) throws ExpertNotFoundException {

        Expert expert = findById(expertId);
        expert.getUser().setPassword(bCryptPasswordEncoder.encode(password));
        return register(expert);
    }

}
