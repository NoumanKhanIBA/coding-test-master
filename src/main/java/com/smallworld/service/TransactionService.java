package com.smallworld.service;

import com.smallworld.entity.Transaction;

import java.util.List;

public interface TransactionService {
    void setTransactions(List<Transaction> transactions);

    List<Transaction> getAllTransactions();

}
