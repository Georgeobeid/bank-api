package com.george.bank.service;

import com.george.bank.model.BankAccount;
import com.george.bank.model.Transaction;
import com.george.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void recordTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setSourceAccount(sourceAccount);
        transaction.setTargetAccount(targetAccount);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionHistory(Long accountId) {
        BankAccount account = new BankAccount();
        account.setId(accountId);
        return transactionRepository.findBySourceAccountOrTargetAccountOrderByTimestampDesc(account, account);
    }
}
