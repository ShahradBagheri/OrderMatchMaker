package com.example.maktabproject.repository;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUser(User user);
}
