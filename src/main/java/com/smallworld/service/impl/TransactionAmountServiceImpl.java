package com.smallworld.service.impl;

import com.smallworld.entity.Transaction;
import com.smallworld.service.TransactionAmountService;
import com.smallworld.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionAmountServiceImpl implements TransactionAmountService {

    private final TransactionService transactionService;

    @Autowired
    public TransactionAmountServiceImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public double getTotalTransactionAmount() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return 0;
        }

        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return 0;
        }

        return transactions.stream()
                .filter(transaction -> transaction.getSenderFullName().equals(senderFullName))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double getMaxTransactionAmount() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return 0;
        }

        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .max()
                .orElse(0);
    }

    @Override
    public List<Transaction> getTop3TransactionsByAmount() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        return transactions.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
}
