package com.example.maktabproject.service;

import com.example.maktabproject.model.VerificationToken;

public interface VerificationTokenService {

    VerificationToken register(VerificationToken verificationToken);

    VerificationToken findByToken(String token);
}
