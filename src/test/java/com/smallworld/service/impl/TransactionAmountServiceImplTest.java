package com.smallworld.service.impl;

import com.smallworld.entity.Transaction;
import com.smallworld.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class TransactionAmountServiceImplTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionAmountServiceImpl transactionAmountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get Total Transaction Amount: returns the sum of transaction amounts")
    void getTotalTransactionAmount_ShouldReturnSumOfTransactionAmounts() {

        when(transactionService.getAllTransactions()).thenReturn(getAllTransactions());

        double expectedTotalAmount = 4371.37;
        double actualTotalAmount = transactionAmountService.getTotalTransactionAmount();

        assertEquals(expectedTotalAmount, actualTotalAmount);
    }

    @Test
    @DisplayName("Get Total Transaction Amount: returns 0 when no transactions are present")
    void getTotalTransactionAmount_ShouldReturnZeroWhenNoTransactionsPresent() {
        when(transactionService.getAllTransactions()).thenReturn(new ArrayList<>());

        double expectedTotalAmount = 0.0;
        double actualTotalAmount = transactionAmountService.getTotalTransactionAmount();

        assertEquals(expectedTotalAmount, actualTotalAmount);
    }

    @Test
    @DisplayName("Get Total Transaction Amount Sent By: should return the sum of transaction amounts sent by the specified sender")
    void getTotalTransactionAmountSentBy_ShouldReturnSumOfTransactionAmountsSentBySender() {
        String senderFullName = "Tom Shelby";

        when(transactionService.getAllTransactions()).thenReturn(getAllTransactions());

        double expectedTotalAmount = 828.26;
        double actualTotalAmount = transactionAmountService.getTotalTransactionAmountSentBy(senderFullName);

        assertEquals(expectedTotalAmount, actualTotalAmount);
    }

    @Test
    @DisplayName("Get Total Transaction Amount Sent By: should return 0 when no transactions are present")
    void getTotalTransactionAmountSentBy_ShouldReturnZeroWhenNoTransactionsPresent() {
        String senderFullName = "Tom Shelby";
        when(transactionService.getAllTransactions()).thenReturn(new ArrayList<>());

        double expectedTotalAmount = 0.0;
        double actualTotalAmount = transactionAmountService.getTotalTransactionAmountSentBy(senderFullName);

        assertEquals(expectedTotalAmount, actualTotalAmount);
    }

    @Test
    @DisplayName("Get Total Transaction Amount Sent By: should return 0 when no transactions are sent by the specified sender")
    void getTotalTransactionAmountSentBy_ShouldReturnZeroWhenNoTransactionsBySender() {
        String senderFullName = "Neeraj";

        when(transactionService.getAllTransactions()).thenReturn(getAllTransactions());

        double expectedTotalAmount = 0.0;
        double actualTotalAmount = transactionAmountService.getTotalTransactionAmountSentBy(senderFullName);

        assertEquals(expectedTotalAmount, actualTotalAmount);
    }

    @Test
    @DisplayName("Get Max Transaction Amount: should return the maximum transaction amount")
    void getMaxTransactionAmount_ShouldReturnMaximumTransactionAmount() {

        when(transactionService.getAllTransactions()).thenReturn(getAllTransactions());

        double expectedMaxAmount = 985.0;
        double actualMaxAmount = transactionAmountService.getMaxTransactionAmount();

        assertEquals(expectedMaxAmount, actualMaxAmount);
    }

    @Test
    @DisplayName("Get Max Transaction Amount: should return 0 when no transactions are present")
    void getMaxTransactionAmount_ShouldReturnZeroWhenNoTransactionsPresent() {
        when(transactionService.getAllTransactions()).thenReturn(new ArrayList<>());

        double expectedMaxAmount = 0.0;
        double actualMaxAmount = transactionAmountService.getMaxTransactionAmount();

        assertEquals(expectedMaxAmount, actualMaxAmount);
    }

    @Test
    @DisplayName("Get Top 3 Transactions By Amount: should return the top 3 transactions with the highest amounts")
    void getTop3TransactionsByAmount_ShouldReturnTop3TransactionsByAmount() {

        when(transactionService.getAllTransactions()).thenReturn(getAllTransactions());

        List<Transaction> expectedTop3Transactions = Arrays.asList(
                new Transaction(5465465, 985.0, "Arthur Shelby", 60, "Ben Younger", 47, 15, false, "Something's fishy"),
                new Transaction(32612651, 666.0, "Grace Burgess", 31, "Michael Gray", 58, 54, false, "Something ain't right"),
                new Transaction(32612651, 666.0, "Grace Burgess", 31, "Michael Gray", 58, 78, true, "Never gonna run around and desert you")

        );
        List<Transaction> actualTop3Transactions = transactionAmountService.getTop3TransactionsByAmount();

        assertEquals(expectedTop3Transactions, actualTop3Transactions);
    }

    @Test
    @DisplayName("Get Top 3 Transactions By Amount: should return an empty list when no transactions are present")
    void getTop3TransactionsByAmount_ShouldReturnEmptyListWhenNoTransactionsPresent() {
        when(transactionService.getAllTransactions()).thenReturn(new ArrayList<>());

        List<Transaction> expectedTop3Transactions = new ArrayList<>();
        List<Transaction> actualTop3Transactions = transactionAmountService.getTop3TransactionsByAmount();

        assertEquals(expectedTop3Transactions, actualTop3Transactions);
    }


    private List<Transaction> getAllTransactions() {
        return Arrays.asList(
                new Transaction(663458, 430.2, "Tom Shelby", 22, "Alfie Solomons", 33, 1, false, "Looks like money laundering"),
                new Transaction(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 2, true, "Never gonna give you up"),
                new Transaction(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 3, false, "Looks like money laundering"),
                new Transaction(96132456, 67.8, "Aunt Polly", 34, "Aberama Gold", 58, null, true, null),
                new Transaction(5465465, 985.0, "Arthur Shelby", 60, "Ben Younger", 47, 15, false, "Something's fishy"),
                new Transaction(1651665, 97.66, "Tom Shelby", 22, "Oswald Mosley", 37, 65, true, "Never gonna let you down"),
                new Transaction(6516461, 33.22, "Aunt Polly", 34, "MacTavern", 30, null, true, null),
                new Transaction(32612651, 666.0, "Grace Burgess", 31, "Michael Gray", 58, 54, false, "Something ain't right"),
                new Transaction(32612651, 666.0, "Grace Burgess", 31, "Michael Gray", 58, 78, true, "Never gonna run around and desert you"),
                new Transaction(32612651, 666.0, "Grace Burgess", 31, "Michael Gray", 58, 99, false, "Don't let this transaction happen"),
                new Transaction(36448252, 154.15, "Billy Kimber", 58, "Winston Churchill", 48, null, true, null),
                new Transaction(645645111, 215.17, "Billy Kimber", 58, "Major Campbell", 41, null, true, null),
                new Transaction(45431585, 89.77, "Billy Kimber", 58, "Luca Changretta", 46, null, true, null)
        );
    }


}
