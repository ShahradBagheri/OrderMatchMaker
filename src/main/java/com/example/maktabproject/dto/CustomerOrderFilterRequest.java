package com.example.maktabproject.dto;

import com.example.maktabproject.model.enumeration.OrderState;

public record CustomerOrderFilterRequest(OrderState orderState) {
}
