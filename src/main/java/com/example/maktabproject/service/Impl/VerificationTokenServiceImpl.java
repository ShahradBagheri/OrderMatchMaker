package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.VerificationToken;
import com.example.maktabproject.repository.VerificationTokenRepository;
import com.example.maktabproject.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken register(VerificationToken verificationToken) {

        return verificationTokenRepository.save(verificationToken);
    }
}
