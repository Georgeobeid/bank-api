package com.george.bank.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestamp;
    private double amount;
    @ManyToOne
    private BankAccount sourceAccount;
    @ManyToOne
    private BankAccount targetAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BankAccount getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(BankAccount sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public BankAccount getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(BankAccount targetAccount) {
        this.targetAccount = targetAccount;
    }
}
