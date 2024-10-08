package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.repository.CustomerRepository;
import com.example.maktabproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Customer register(Customer customer) {

        if (customer.getId() == null) {
            String password = customer.getUser().getPassword();
            customer.getUser().setPassword(bCryptPasswordEncoder.encode(password));
        }

        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {

        customerRepository.delete(customer);
    }

    @Override
    public Customer findById(Long id) {

        return customerRepository.findById(id).orElseThrow(
                () -> new CustomExceptions.CustomerNotFoundException("customer not found")
        );
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUser_Username(username).orElseThrow(
                () -> new CustomExceptions.CustomerNotFoundException("customer not found")
        );
    }

    @Override
    public List<Customer> findAll() {

        return customerRepository.findAll();
    }

    @Override
    public Customer findByUser(Long userId) {

        return customerRepository.findByUser_Id(userId).orElseThrow(
                () -> new CustomExceptions.CustomerNotFoundException("customer not found")
        );
    }

    @Override
    public Customer changePassword(Long customerId, String password) {

        Customer customer = findById(customerId);
        customer.getUser().setPassword(bCryptPasswordEncoder.encode(password));
        return register(customer);
    }
}
