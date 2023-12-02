package com.example.maktabproject.dto.user;

import com.example.maktabproject.model.enumeration.OrderState;

public record UserOrderFilterRequestDto(OrderState orderState) {
}
