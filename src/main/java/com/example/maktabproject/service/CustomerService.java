package com.example.maktabproject.service;

import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.User;

import java.util.List;

public interface CustomerService {

    Customer register(Customer customer);

    void delete(Customer customer);

    Customer findById(Long id) throws CustomerNotFoundException;

    List<Customer> findAll();

    Customer findByUser(User user);

    Customer changePassword(Customer customer,String password);
}
