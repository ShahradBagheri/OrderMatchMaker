package com.example.maktabproject.repository;

import com.example.maktabproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUser_Id(Long userId);

    Optional<Customer> findByUser_Username(String username);
}
