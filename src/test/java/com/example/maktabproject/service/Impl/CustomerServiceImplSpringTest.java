package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class CustomerServiceImplSpringTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    void validCustomerRegisterShouldSave() {

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahrad2@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customer = customerService.register(customer);
        assertThat(customer.getId()).isNotNull();
    }

    @Test
    void duplicateEmailShouldNotSave() {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("first@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        Customer dupCustomer = Customer.builder()
                .user(user)
                .build();

        customerService.register(customer);
        customerService.register(dupCustomer);
        assertThat(dupCustomer.getId()).isNull();
    }

    @Test
    void invalidPasswordShouldNotSave() {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahrad21@gmaill.com")
                .password("qweasdqwe")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customerService.register(customer);
        assertThat(customer.getId()).isNull();
    }

    @Test
    void invalidEmailShouldNotSave() {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahrad2gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customerService.register(customer);
        assertThat(customer.getId()).isNull();
    }

    @Test
    void customerShouldBeFound() throws CustomerNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahradFind@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customerService.register(customer);
        Customer foundCustomer = customerService.findById(customer.getId());

        assertThat(foundCustomer).isNotNull();
    }

    @Test
    void customersShouldBeFound() {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahradTestingAll@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customerService.register(customer);

        List<Customer> all = customerService.findAll();

        assertThat(all).isNotNull();
    }

    @Test
    void CustomerShouldGetDeleted() throws CustomerNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahrad34534534@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        Customer registerdCustomer = customerService.register(customer);
        customerService.delete(customerService.findById(registerdCustomer.getId()));
        assertThatThrownBy(() -> customerService.findById(registerdCustomer.getId())).isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void CustomerPasswordShouldChange() throws CustomerNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahradchanged@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .build();

        customer = customerService.register(customer);
        customerService.changePassword(customer, "changed123");
        Customer changedCustomer = customerService.findById(customer.getId());
        assertThat(changedCustomer.getUser().getPassword()).isEqualTo("changed123");
    }

    @Test
    void CustomerFoundByUser() throws CustomerNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("findUserTest@gmaill.com")
                .password("qweasd123")
                .build();
        Customer customer = Customer.builder()
                .user(user)
                .orders(null)
                .build();

        customer = customerService.register(customer);
        Customer foundCustomer = customerService.findByUser(customer.getUser());
        assertThat(customer.getUser()).isEqualTo(foundCustomer.getUser());
    }

}