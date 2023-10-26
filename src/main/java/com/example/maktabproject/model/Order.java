package com.example.maktabproject.model;

import com.example.maktabproject.model.enumeration.OrderState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    private SubService subService;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    private Offer selectedOffer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<Offer> offers;

    private Double suggestedPrice;

    private String details;

    private LocalDateTime startingDate;

    private String address;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;
}
