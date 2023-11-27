package com.smallworld.service.impl;

import com.smallworld.entity.Transaction;
import com.smallworld.service.TransactionIssueService;
import com.smallworld.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransactionIssueServiceImp implements TransactionIssueService {

    private final TransactionService transactionService;

    @Autowired
    public TransactionIssueServiceImp(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public Set<Integer> getUnsolvedIssueIds() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return Collections.emptySet();
        }

        return transactions.stream()
                .filter(transaction -> transaction.getIssueId() != null && !transaction.isIssueSolved())
                .map(Transaction::getIssueId)
                .collect(Collectors.toSet());
    }

    @Override
    public List<String> getAllSolvedIssueMessages() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return Collections.emptyList();
        }

        return transactions.stream()
                .filter(Transaction::isIssueSolved)
                .map(Transaction::getIssueMessage)
                .toList();
    }
}
