package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.User;
import com.example.maktabproject.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

//    @Mock
//    private CustomerRepository customerRepository;
//
//    private CustomerServiceImpl customerService;
//
//
//    @BeforeEach
//    void setUp() {
//        customerService = new CustomerServiceImpl(customerRepository);
//    }
//
//    @Test
//    void validCustomerRegisterShouldSave() {
//        Customer customer = Customer.builder().build();
//
//        customerService.register(customer);
//
//        verify(customerRepository).save(customer);
//    }
//
//    @Test
//    void exceptionThrownRegisterShouldReturnNull(){
//        when(customerRepository.save(any(Customer.class))).thenThrow(new IllegalArgumentException());
//
//        Customer register = customerService.register(Customer.builder().build());
//
//        assertThat(register).isNull();
//    }
}