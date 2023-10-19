package com.example.maktabproject.model;

import com.example.maktabproject.model.enumeration.ExpertStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Expert{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExpertStatus expertStatus;

    @Lob
    private byte[] imageData;

    private Float score = 0F;

    @OneToMany(mappedBy = "expert")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "expert")
    private List<Offer> offers;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<SubService> subServices;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_Id")
    private User user;

}
