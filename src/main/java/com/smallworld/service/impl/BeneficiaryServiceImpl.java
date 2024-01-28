package com.smallworld.service.impl;

import com.smallworld.entity.Transaction;
import com.smallworld.service.BeneficiaryService;
import com.smallworld.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    private  TransactionService transactionService;

    @Override
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return Collections.emptyMap();
        }

        return transactions.stream()
                .collect(Collectors.toMap(Transaction::getBeneficiaryFullName, transaction -> transaction));
    }
}
