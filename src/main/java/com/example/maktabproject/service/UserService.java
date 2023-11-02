package com.example.maktabproject.service;

import com.example.maktabproject.exception.IncorrectCredentialsException;
import com.example.maktabproject.exception.UserNotFoundException;
import com.example.maktabproject.model.User;

public interface UserService {

    User register(User user);

    User login(String email, String password) throws IncorrectCredentialsException;

    User findById(Long id) throws UserNotFoundException;
}
