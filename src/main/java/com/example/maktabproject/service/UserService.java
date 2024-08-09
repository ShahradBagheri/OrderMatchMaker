package com.example.maktabproject.service;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.User;

public interface UserService {

    User register(User user);

    User login(String email, String password) throws CustomExceptions.IncorrectCredentialsException;

    User findById(Long id) throws CustomExceptions.UserNotFoundException;
}
