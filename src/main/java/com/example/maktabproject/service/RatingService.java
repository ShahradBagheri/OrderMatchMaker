package com.example.maktabproject.service;

import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.exception.InvalidScoreException;
import com.example.maktabproject.exception.RatingNotFoundException;
import com.example.maktabproject.model.Rating;

import java.util.List;

public interface RatingService {

    Rating register(Rating rating) throws ExpertNotFoundException, InvalidScoreException;

    void delete(Rating rating);

    Rating findById(Long id) throws RatingNotFoundException;

    List<Rating> findAll();
}
