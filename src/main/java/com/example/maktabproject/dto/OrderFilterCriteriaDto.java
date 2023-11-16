package com.example.maktabproject.dto;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.enumeration.OrderState;

import java.time.LocalDateTime;

public record OrderFilterCriteriaDto(Customer customer,
                                     Expert expert,
                                     LocalDateTime doneAfter,
                                     LocalDateTime doneBefore,
                                     OrderState orderState,
                                     MainService mainService,
                                     SubService subService) {
}
