package com.george.bank.repository;

import com.george.bank.model.BankAccount;
import com.george.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySourceAccountOrTargetAccountOrderByTimestampDesc(
            BankAccount sourceAccount, BankAccount targetAccount);
}
