package com.example.maktabproject.service;

import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer register(Customer customer);

    void delete(Customer customer);

    Customer findById(Long id) throws CustomerNotFoundException;

    Customer findByUsername(String username);

    List<Customer> findAll();

    Customer findByUser(Long userId) throws CustomerNotFoundException;

    Customer changePassword(Long customerId, String password) throws CustomerNotFoundException;
}
