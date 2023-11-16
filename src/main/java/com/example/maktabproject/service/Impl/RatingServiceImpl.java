package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.InvalidScoreException;
import com.example.maktabproject.exception.RatingNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Rating;
import com.example.maktabproject.repository.RatingRepository;
import com.example.maktabproject.service.ExpertService;
import com.example.maktabproject.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ExpertService expertService;

    @Override
    public Rating register(Rating rating) {

        if (rating.getScore() > 5 || rating.getScore() < 0)
            throw new InvalidScoreException("invalid score!");

        Expert expert = expertService.findById(rating.getExpert().getId());
        expert.setScore(expert.getScore() + rating.getScore());

        expertService.register(expert);
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Rating rating) {

        ratingRepository.delete(rating);
    }

    @Override
    public Rating findById(Long id) throws RatingNotFoundException {

        return ratingRepository.findById(id).orElseThrow(
                () -> new RatingNotFoundException("rating not found!")
        );
    }

    @Override
    public List<Rating> findAll() {

        return ratingRepository.findAll();
    }
}
