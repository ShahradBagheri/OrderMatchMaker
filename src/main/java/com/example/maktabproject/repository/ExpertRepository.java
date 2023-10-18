package com.example.maktabproject.repository;

import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpertRepository extends JpaRepository<Expert, Long> {

    Optional<Expert> findByUser(User user);
}
