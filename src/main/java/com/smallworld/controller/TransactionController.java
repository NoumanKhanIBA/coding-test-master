package com.smallworld.controller;


import com.smallworld.common.Error_Message;
import com.smallworld.common.API_URI;
import com.smallworld.entity.Transaction;
import com.smallworld.exception.TransactionLoaderException;
import com.smallworld.service.BeneficiaryService;
import com.smallworld.service.TransactionAmountService;
import com.smallworld.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(API_URI.TRANSACTION)
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionAmountService transactionAmountService;



    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactionList = transactionService.getAllTransactions();
        if (transactionList.isEmpty()) {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return ResponseEntity.ok(transactionList);
    }

    @GetMapping("/totalTransactionAmount")
    public ResponseEntity<Double> getTotalTransactionAmount() {
        return ResponseEntity.ok(transactionAmountService.getTotalTransactionAmount());
    }

    @GetMapping("/totalTransactionAmountSentBy")
    public ResponseEntity<Double> getTotalTransactionAmountSentBy(@RequestParam String senderFullName) {
        return ResponseEntity.ok(transactionAmountService.getTotalTransactionAmountSentBy(senderFullName));
    }

    @GetMapping("/maxTransactionAmount")
    public ResponseEntity<Double> getMaxTransactionAmount() {
        return ResponseEntity.ok(transactionAmountService.getMaxTransactionAmount());
    }

    @GetMapping("/top3TransactionsByAmount")
    public ResponseEntity<List<Transaction>> getTop3TransactionsByAmount() {
        return ResponseEntity.ok(transactionAmountService.getTop3TransactionsByAmount());
    }
}
