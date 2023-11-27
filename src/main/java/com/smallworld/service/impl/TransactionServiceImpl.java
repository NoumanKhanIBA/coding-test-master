package com.smallworld.service.impl;

import com.smallworld.entity.Transaction;
import com.smallworld.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    private List<Transaction> transactions;

    public TransactionServiceImpl() {
        this.transactions = new ArrayList<>();
    }


    @Override
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }


    @Override
    public List<Transaction> getAllTransactions() {

        return transactions;
    }
}
