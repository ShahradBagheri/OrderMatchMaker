package com.example.maktabproject.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double credit = 0.0;
}
