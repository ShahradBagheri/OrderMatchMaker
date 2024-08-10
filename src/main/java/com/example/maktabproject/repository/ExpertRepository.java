package com.example.maktabproject.repository;

import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.enums.ExpertStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ExpertRepository extends JpaRepository<Expert, Long>, JpaSpecificationExecutor<Expert> {

    Optional<Expert> findByUser_Id(Long userId);

    Optional<Expert> findByUser_Username(String username);

    Optional<List<Expert>> findByExpertStatus(ExpertStatus expertStatus);
}
