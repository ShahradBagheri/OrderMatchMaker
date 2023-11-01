package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Rating;
import com.example.maktabproject.repository.RatingRepository;
import com.example.maktabproject.service.ExpertService;
import com.example.maktabproject.service.RatingService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ExpertService expertService;

    @Override
    public Rating register(Rating rating) throws ExpertNotFoundException {
        Expert expert = expertService.findById(rating.getExpert().getId());
        expert.setScore(expert.getScore() + rating.getScore());

        expertService.register(expert);
        try{
            return ratingRepository.save(rating);
        } catch (ConstraintViolationException | DataAccessException e){
            log.error(e.getMessage());
            return null;
        }
    }
}