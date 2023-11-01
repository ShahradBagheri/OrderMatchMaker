package com.example.maktabproject.service;

import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.model.Rating;

public interface RatingService {

    Rating register(Rating rating) throws ExpertNotFoundException;
}
