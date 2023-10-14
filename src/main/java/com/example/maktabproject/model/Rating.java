package com.example.maktabproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;

    private Double score;

    private String comment;
}
