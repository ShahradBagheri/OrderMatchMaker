package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomerNotFoundException;
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

        customerRepository.delete(customer);
    }

    @Override
    public Customer findById(Long id) throws CustomerNotFoundException {

        return customerRepository.findById(id).orElseThrow(
                CustomerNotFoundException::new
        );
    }

    @Override
    public List<Customer> findAll() {

        return customerRepository.findAll();
    }

    @Override
    public Customer findByUser(User user) throws CustomerNotFoundException {

        return customerRepository.findByUser(user).orElseThrow(
                CustomerNotFoundException::new
        );
    }

    @Override
    public Customer changePassword(Customer customer, String password) {

        Customer customer1 = customerRepository.findById(customer.getId()).orElseThrow();
        customer1.getUser().setPassword(password);
        return register(customer1);
    }
}
