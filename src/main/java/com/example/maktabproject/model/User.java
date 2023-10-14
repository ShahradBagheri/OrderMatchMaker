package com.example.maktabproject.model;

import com.example.maktabproject.model.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    //@Pattern
    private String email;

    private String password;

    private String firstname;

    private String lastname;

    @CreationTimestamp
    private LocalDateTime registrationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    private Role role;
}
