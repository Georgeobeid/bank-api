package com.george.bank.repository;

import com.george.bank.model.BankAccount;
import com.george.bank.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BankAccountRepositoryTest {
    @Autowired
    private BankAccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;


    private Customer customer;

    @BeforeEach
    void setup() {
        customer = customerRepository.save(new Customer(1L, "Branden Gibson"));
    }

    @Test
    public void shouldSaveBankAccountSuccessfully() {
        BankAccount account = new BankAccount();
        account.setOwner(customer);
        account.setBalance(1000.0);
        BankAccount savedAccount = accountRepository.save(account);

        // Assertions
        Assertions.assertNotNull(savedAccount);
        Assertions.assertEquals(customer, savedAccount.getOwner());
        Assertions.assertEquals(1000.00, savedAccount.getBalance());
        Assertions.assertEquals(account, savedAccount);
    }

    //TODO: Write more test methods for different scenarios.
}
