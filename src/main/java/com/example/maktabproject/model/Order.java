package com.example.maktabproject.model;

import com.example.maktabproject.model.enumeration.OrderState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private SubService subService;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @OneToMany(mappedBy = "order")
    private List<Offer> offers;

    private Double suggestedPrice;

    private String details;

    private LocalDateTime startingDate;

    private String address;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;
}
