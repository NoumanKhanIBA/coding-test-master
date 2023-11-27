package com.smallworld.service;

import com.smallworld.entity.Transaction;

import java.util.List;


public interface TransactionAmountService {
    double getTotalTransactionAmount();

    double getTotalTransactionAmountSentBy(String senderFullName);

    double getMaxTransactionAmount();

    List<Transaction> getTop3TransactionsByAmount();
}
