package com.example.maktabproject.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Double score;

    private String comment;
}
