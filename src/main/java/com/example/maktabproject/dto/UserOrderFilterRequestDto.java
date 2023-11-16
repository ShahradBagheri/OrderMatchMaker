package com.example.maktabproject.dto;

import com.example.maktabproject.model.enumeration.OrderState;

public record UserOrderFilterRequestDto(OrderState orderState) {
}
