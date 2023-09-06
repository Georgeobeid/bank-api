package com.george.bank.service;

import com.george.bank.model.Customer;
import com.george.bank.model.dto.CustomerCreationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = customerService.createCustomer(new CustomerCreationRequest("Georgina Hazel"));
    }

    @Test
    void shouldRetrieveCustomerSuccessfully() {
        Assertions.assertNotNull(customerService.getCustomerById(customer.getId()));
    }

    @Test
    void shouldThrowRunExceptionError() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            customerService.getCustomerById(232L);
        });
    }
}
