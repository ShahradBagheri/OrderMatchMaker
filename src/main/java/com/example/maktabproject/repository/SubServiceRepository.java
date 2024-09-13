package com.example.maktabproject.repository;

import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubServiceRepository extends JpaRepository<SubService, Long> {

    List<SubService> findAllByMainService(MainService mainService);

    List<SubService> findAllByExperts_Id(Long expertId);
}
