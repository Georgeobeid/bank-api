package com.george.bank.repository;

import com.george.bank.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = customerRepository.save(new Customer(1L, "Rhonda Church"));
    }

    @Test
    void shouldSaveCustomerSuccessfully() {
        Assertions.assertNotNull(customer);
    }

    @Test
    void shouldRetrieveCustomerSuccessfully() {
        Optional<Customer> foundItem = customerRepository.findById(customer.getId());
        Assertions.assertTrue(foundItem.isPresent());
        Assertions.assertEquals(customer.getName(), foundItem.get().getName());
    }
    //TODO: Write more test methods for different scenarios.

}
