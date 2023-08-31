package com.george.bank.controller;

import com.george.bank.model.BankAccount;
import com.george.bank.model.Transaction;
import com.george.bank.model.dto.AccountCreationRequest;
import com.george.bank.model.dto.TransferRequest;
import com.george.bank.service.BankAccountService;
import com.george.bank.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-account")
public class BankAccountController {

    private final BankAccountService accountService;

    private final TransactionService transactionService;

    public BankAccountController(BankAccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody AccountCreationRequest newAccount) {
        BankAccount account = accountService.createAccount(newAccount.getCustomerId(), newAccount.getInitialBalance());
        return ResponseEntity.status(HttpStatus.CREATED).body("Account created with ID: " + account.getId());
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransferRequest request) {
        BankAccount sourceAccount = accountService.getAccountById(request.getSourceAccountId());
        BankAccount targetAccount = accountService.getAccountById(request.getTargetAccountId());

        boolean success = accountService.transferFunds(sourceAccount, targetAccount, request.getAmount());

        if (success) {
            return ResponseEntity.ok("Funds transferred successfully");
        } else {
            return ResponseEntity.badRequest().body("Insufficient balance");
        }
    }

    @GetMapping("/balance/{accountId}")
    public ResponseEntity<Double> getBalance(@PathVariable Long accountId) {
        double balance = accountService.getAccountBalance(accountId);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable Long accountId) {
        List<Transaction> transactionHistory = transactionService.getTransactionHistory(accountId);
        return ResponseEntity.ok(transactionHistory);
    }

}
