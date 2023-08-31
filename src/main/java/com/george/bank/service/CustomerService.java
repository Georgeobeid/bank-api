package com.george.bank.service;

import com.george.bank.model.Customer;
import com.george.bank.model.dto.CustomerCreationRequest;
import com.george.bank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CustomerCreationRequest newCustomer) {
        Customer customer = new Customer();
        customer.setName(newCustomer.getName());
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long customerId){
        //TODO: CREATE CUSTOM ERROR HANDLING
        return this.customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

}
