package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.IncorrectCrudencialException;
import com.example.maktabproject.model.User;
import com.example.maktabproject.repository.UserRepository;
import com.example.maktabproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User login(String email, String password) throws IncorrectCrudencialException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IncorrectCrudencialException("incorrect email or password")
        );
        return null;
    }
}
