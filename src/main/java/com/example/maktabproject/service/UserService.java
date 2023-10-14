package com.example.maktabproject.service;

import com.example.maktabproject.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User register(User user);
}
