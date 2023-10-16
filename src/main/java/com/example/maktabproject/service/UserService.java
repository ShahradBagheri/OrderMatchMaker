package com.example.maktabproject.service;

import com.example.maktabproject.exception.IncorrectCredentialsException;
import com.example.maktabproject.model.User;

public interface UserService {

    User login(String email, String password) throws IncorrectCredentialsException;
}
