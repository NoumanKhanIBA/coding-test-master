package com.smallworld.service.impl;


import com.smallworld.service.TransactionService;
import com.smallworld.util.TransactionLoader;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionInitializer {

    private final TransactionService transactionService;
    private final TransactionLoader transactionLoader;

    @PostConstruct
    public void initializeTransactions() {

        log.info("Loading transaction file...");
        transactionService.setTransactions(transactionLoader.loadTransactions());
        log.info("Transaction file loaded successfully");

    }

}
