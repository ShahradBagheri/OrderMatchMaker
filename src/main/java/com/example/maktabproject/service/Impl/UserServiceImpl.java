package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.User;
import com.example.maktabproject.repository.UserRepository;
import com.example.maktabproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User register(User user) {
        try {
            return userRepository.save(user);
        }catch (Exception e){
            return null;
        }
    }
}
