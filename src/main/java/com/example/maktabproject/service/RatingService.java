package com.example.maktabproject.service;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.Rating;

import java.util.List;

public interface RatingService {

    Rating register(Rating rating) throws CustomExceptions.ExpertNotFoundException, CustomExceptions.InvalidScoreException;

    void delete(Rating rating);

    Rating findById(Long id) throws CustomExceptions.RatingNotFoundException;

    List<Rating> findAll();
}
