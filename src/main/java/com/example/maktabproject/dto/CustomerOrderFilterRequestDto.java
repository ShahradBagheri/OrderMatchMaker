package com.example.maktabproject.dto;

import com.example.maktabproject.model.enumeration.OrderState;

public record CustomerOrderFilterRequestDto(OrderState orderState) {
}
