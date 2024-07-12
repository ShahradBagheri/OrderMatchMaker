package com.example.maktabproject.model.dto.user;

import com.example.maktabproject.model.dto.rating.RatingRequestDto;
import com.example.maktabproject.model.dto.rating.RatingResponseDto;
import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Rating;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingMapper {

    private final ExpertServiceImpl expertService;
    private final CustomerServiceImpl customerService;

    public Rating dtoToRating(RatingRequestDto ratingRequestDto) throws ExpertNotFoundException, CustomerNotFoundException {

        Expert expert = expertService.findById(ratingRequestDto.expertId());

        String comment = "";

        if (ratingRequestDto.comment() != null)
            comment = ratingRequestDto.comment();

        return Rating.builder()
                .expert(expert)
                .score(ratingRequestDto.score())
                .comment(comment)
                .build();
    }

    public RatingResponseDto ratingToDto(Rating rating) {

        return RatingResponseDto.builder()
                .id(rating.getId())
                .customerId(rating.getCustomer().getId())
                .expertId(rating.getExpert().getId())
                .score(rating.getScore())
                .comment(rating.getComment())
                .build();
    }
}
