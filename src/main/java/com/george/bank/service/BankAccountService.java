package com.george.bank.service;

import com.george.bank.model.BankAccount;
import com.george.bank.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountService {

    private final BankAccountRepository accountRepository;

    private final TransactionService transactionService;

    private final CustomerService customerService;

    public BankAccountService(BankAccountRepository accountRepository, TransactionService transactionService, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    public BankAccount createAccount(Long customerId, double initialBalance) {
        BankAccount account = new BankAccount();
        account.setOwner(this.customerService.getCustomerById(customerId));
        account.setBalance(initialBalance);
        account.setAccountNumber(generateAccountNumber());
        return accountRepository.save(account);
    }

    public boolean transferFunds(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
        if (sourceAccount.getBalance() >= amount) {
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            targetAccount.setBalance(targetAccount.getBalance() + amount);

            accountRepository.save(sourceAccount);
            accountRepository.save(targetAccount);

            transactionService.recordTransaction(sourceAccount, targetAccount, amount);
            return true;
        } else {
            return false; // Insufficient balance
        }
    }

    public double getAccountBalance(Long accountId) {
        Optional<BankAccount> accountOptional = accountRepository.findById(accountId);
        return accountOptional.map(BankAccount::getBalance).orElse(0.0);
    }

    public BankAccount getAccountById(Long accountId) {
        //TODO: CREATE CUSTOM ERROR HANDLING
        return this.accountRepository.findById(accountId).orElseThrow(() -> new NoSuchElementException("Bank Account not found"));
    }

    private String generateAccountNumber() {
        // Generate account number logic
        return "ACC" + UUID.randomUUID().toString();
    }

}
