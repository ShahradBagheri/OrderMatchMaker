package com.example.maktabproject.service;

import com.example.maktabproject.exception.IncorrectCrudencialException;
import com.example.maktabproject.model.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User login(String email, String password) throws IncorrectCrudencialException;
}
