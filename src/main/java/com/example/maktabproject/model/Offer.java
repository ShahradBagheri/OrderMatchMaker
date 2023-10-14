package com.example.maktabproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;

    private LocalDate creationDate;

    private LocalDate startingDate;

    private LocalDate completionDate;

    private Double suggestedPrice;

    @PrePersist
    private void onCreate(){
        creationDate = LocalDate.now();
    }
}
