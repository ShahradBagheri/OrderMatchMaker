package com.example.maktabproject.dto.order;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.enumeration.OrderState;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderFilterCriteriaDto(Customer customer,
                                     Expert expert,
                                     LocalDateTime startAfter,
                                     LocalDateTime startBefore,
                                     OrderState orderState,
                                     MainService mainService,
                                     SubService subService) {
}
