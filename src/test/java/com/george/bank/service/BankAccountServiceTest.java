package com.george.bank.service;

import com.george.bank.model.BankAccount;
import com.george.bank.model.Customer;
import com.george.bank.repository.BankAccountRepository;
import com.george.bank.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
public class BankAccountServiceTest {

    @Autowired
    private BankAccountService accountService;

    @Mock
    private BankAccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private TransactionService transactionService;
    private Customer owner;

    @BeforeEach
    void setUp() {
        owner = customerRepository.save(new Customer(1L, "Arisha Barron"));
    }

    @Test
    public void shouldCreateAccountSuccessfully() {
        double initialBalance = 100.0;
        BankAccount createdAccount = accountService.createAccount(owner.getId(), initialBalance);
        Assertions.assertNotNull(createdAccount);
        Assertions.assertEquals(initialBalance, createdAccount.getBalance());
        // Add more assertions if needed
    }

    @Test
    public void shouldTransferFundsSuccessfully() {
        BankAccount sourceAccount = new BankAccount();
        sourceAccount.setBalance(200.0);

        BankAccount targetAccount = new BankAccount();
        targetAccount.setBalance(100.0);

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(sourceAccount));
        Mockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(targetAccount));

        boolean success = accountService.transferFunds(sourceAccount, targetAccount, 50.0);

        Assertions.assertTrue(success);
        Assertions.assertEquals(150.0, sourceAccount.getBalance(), 0.01);
        Assertions.assertEquals(150.0, targetAccount.getBalance(), 0.01);
        // Add more assertions if needed
    }

    @Test
    public void shouldNotTransferFunds() {
        BankAccount sourceAccount = new BankAccount();
        sourceAccount.setBalance(200.0);

        BankAccount targetAccount = new BankAccount();
        targetAccount.setBalance(100.0);

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(sourceAccount));
        Mockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(targetAccount));

        boolean success = accountService.transferFunds(sourceAccount, targetAccount, 300);

        Assertions.assertFalse(success);
        Assertions.assertEquals(200.0, sourceAccount.getBalance(), 0.01);
        Assertions.assertEquals(100.0, targetAccount.getBalance(), 0.01);
        // Add more assertions if needed
    }

    //TODO: Write more test methods for different scenarios.
}

