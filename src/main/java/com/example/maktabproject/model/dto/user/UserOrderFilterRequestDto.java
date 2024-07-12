package com.example.maktabproject.model.dto.user;

import com.example.maktabproject.model.enums.OrderState;

public record UserOrderFilterRequestDto(OrderState orderState) {
}
