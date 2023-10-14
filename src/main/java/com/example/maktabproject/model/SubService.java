package com.example.maktabproject.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"mainService","experts"})
@Entity
public class SubService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Double basePrice;

    private String comment;

    @ManyToMany
    private List<Expert> experts;

    @ManyToOne
    @JoinColumn(name = "main_service_id")
    private MainService mainService;
}
