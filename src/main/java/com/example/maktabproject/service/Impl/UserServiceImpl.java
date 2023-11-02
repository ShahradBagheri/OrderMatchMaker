package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.IncorrectCredentialsException;
import com.example.maktabproject.exception.UserNotFoundException;
import com.example.maktabproject.model.User;
import com.example.maktabproject.repository.UserRepository;
import com.example.maktabproject.service.UserService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User register(User user) {

        try{
            return userRepository.save(user);
        } catch (ConstraintViolationException | DataAccessException e){
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public User login(String email, String password) throws IncorrectCredentialsException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new IncorrectCredentialsException("incorrect email or password")
        );
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(
                UserNotFoundException::new
        );
    }


}
