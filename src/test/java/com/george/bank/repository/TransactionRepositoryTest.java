package com.george.bank.repository;

import com.george.bank.model.BankAccount;
import com.george.bank.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository accountRepository;
    private BankAccount sourceAccount;

    private BankAccount targetAccount;

    @BeforeEach
    void setUp() {
        sourceAccount = new BankAccount();
        sourceAccount.setId(1L);
        sourceAccount.setBalance(1000.0);
        accountRepository.save(sourceAccount);
        targetAccount = new BankAccount();
        targetAccount.setId(2L);
        targetAccount.setBalance(500.0);
        accountRepository.save(targetAccount);

    }

    @Test
    public void shouldSaveTransactionCorrectly() {

        // Create and save a transaction
        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount);
        transaction.setTargetAccount(targetAccount);
        transaction.setAmount(200.0);
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Assertions
        Assertions.assertNotNull(savedTransaction);
        Assertions.assertEquals(sourceAccount, savedTransaction.getSourceAccount());
        Assertions.assertEquals(targetAccount, savedTransaction.getTargetAccount());
        Assertions.assertEquals(200.0, savedTransaction.getAmount());
    }
    //TODO: Write more test methods for different scenarios.

}
