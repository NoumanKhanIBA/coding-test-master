package com.smallworld.controller;


import com.smallworld.common.Error_Message;
import com.smallworld.common.API_URI;
import com.smallworld.entity.Transaction;
import com.smallworld.exception.TransactionLoaderException;
import com.smallworld.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(API_URI.TRANSACTION)
public class TransactionController {



    private final TransactionService transactionService;


    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping
    List<Transaction> getAllTransactions() {
        List<Transaction> transactionList = transactionService.getAllTransactions();
        if (transactionList.isEmpty()) {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return transactionList;

    }

}
