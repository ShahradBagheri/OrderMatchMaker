package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.enums.ExpertStatus;
import com.example.maktabproject.repository.ExpertRepository;
import com.example.maktabproject.service.ExpertService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public Expert register(Expert expert) {
        if (expert.getId() == null) {
            String password = expert.getUser().getPassword();
            expert.getUser().setPassword(bCryptPasswordEncoder.encode(password));
        }

        if (expert.getScore() < 0)
            expert.setExpertStatus(ExpertStatus.INACTIVE);
        return expertRepository.save(expert);
    }

    @Override
    @Transactional
    public void delete(Expert expert) {

        expertRepository.delete(expert);
    }

    @Override
    @Transactional
    public Expert findById(Long id) {

        return expertRepository.findById(id).orElseThrow(
                () -> new CustomExceptions.ExpertNotFoundException("expert not found")
        );
    }

    @Override
    @Transactional
    public Expert findByUsername(String username) {
        return expertRepository.findByUser_Username(username).orElseThrow(
                () -> new CustomExceptions.ExpertNotFoundException("expert not found")
        );
    }

    @Override
    @Transactional
    public List<Expert> findAll() {

        return expertRepository.findAll();
    }

    @Override
    @Transactional
    public Expert findByUser(Long userId) {

        return expertRepository.findByUser_Id(userId).orElseThrow(
                () -> new CustomExceptions.ExpertNotFoundException("expert not found")
        );
    }

    @Override
    public Expert changePassword(Long expertId, String password) {

        Expert expert = findById(expertId);
        expert.getUser().setPassword(bCryptPasswordEncoder.encode(password));
        return register(expert);
    }

}
