package com.george.bank.controller;

import com.george.bank.model.Customer;
import com.george.bank.model.dto.CustomerCreationRequest;
import com.george.bank.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerCreationRequest newCustomer) {
        customerService.createCustomer(newCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created");
    }

    @GetMapping("/get-customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }
}
