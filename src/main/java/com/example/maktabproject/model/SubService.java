package com.example.maktabproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"mainService", "experts"})
@Entity
public class SubService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Double basePrice;

    private String comment;

    @ManyToMany(mappedBy = "subServices")
    private List<Expert> experts;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "main_service_id")
    private MainService mainService;
}
