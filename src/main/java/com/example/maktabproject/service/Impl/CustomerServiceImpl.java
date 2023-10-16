package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.User;
import com.example.maktabproject.repository.CustomerRepository;
import com.example.maktabproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer register(Customer customer) {
        try{
            return customerRepository.save(customer);
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public Customer findById(Long id) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findByUser(User user) {
        return null;
    }

    @Override
    public Customer changePassword(Customer customer, String password) {
        return null;
    }
}
