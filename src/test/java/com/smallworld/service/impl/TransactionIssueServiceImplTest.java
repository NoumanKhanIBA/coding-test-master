package com.smallworld.service.impl;

import com.smallworld.entity.Transaction;
import com.smallworld.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TransactionIssueServiceImplTest {
    @Mock
    private TransactionService transactionService;

    private TransactionIssueServiceImp transactionIssueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionIssueService = new TransactionIssueServiceImp(transactionService);
    }

    @Test
    @DisplayName("Get unsolved issue IDs: Transactions with no unsolved issues - Returns empty set")
    void getUnsolvedIssueIds_TransactionsWithNoUnsolvedIssues_ReturnsEmptySet() {
        when(transactionService.getAllTransactions()).thenReturn(new ArrayList<>());

        Set<Integer> unsolvedIssueIds = transactionIssueService.getUnsolvedIssueIds();

        assertNotNull(unsolvedIssueIds);
        assertTrue(unsolvedIssueIds.isEmpty());
    }

    @Test
    @DisplayName("Get unsolved issue IDs: No transactions - Returns empty set")
    void getUnsolvedIssueIds_NoTransactions_ReturnsEmptySet() {
        when(transactionService.getAllTransactions()).thenReturn(Collections.emptyList());

        Set<Integer> unsolvedIssueIds = transactionIssueService.getUnsolvedIssueIds();

        assertNotNull(unsolvedIssueIds);
        assertTrue(unsolvedIssueIds.isEmpty());
    }

    @Test
    @DisplayName("Get unsolved issue IDs: Transactions with unsolved issues - Returns set with issue IDs")
    void getUnsolvedIssueIds_TransactionsWithUnsolvedIssues_ReturnsSetWithIssueIds() {
        List<Transaction> transactions = getAllTransactionsWithIssues();
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        Set<Integer> unsolvedIssueIds = transactionIssueService.getUnsolvedIssueIds();

        assertNotNull(unsolvedIssueIds);
        assertEquals(1, unsolvedIssueIds.size());
        assertTrue(unsolvedIssueIds.contains(3));
    }

    @Test
    @DisplayName("Get all solved issue messages: No transactions - Returns empty list")
    void getAllSolvedIssueMessages_NoTransactions_ReturnsEmptyList() {
        when(transactionService.getAllTransactions()).thenReturn(Collections.emptyList());

        List<String> solvedIssueMessages = transactionIssueService.getAllSolvedIssueMessages();

        assertNotNull(solvedIssueMessages);
        assertTrue(solvedIssueMessages.isEmpty());
    }

    @Test
    @DisplayName("Get all solved issue messages: Transactions with no solved issues - Returns empty list")
    void getAllSolvedIssueMessages_TransactionsWithNoSolvedIssues_ReturnsEmptyList() {
        List<Transaction> transactions = getAllTransactions();
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        List<String> solvedIssueMessages = transactionIssueService.getAllSolvedIssueMessages();

        assertNotNull(solvedIssueMessages);
        assertTrue(solvedIssueMessages.isEmpty());
    }

    @Test
    @DisplayName("Get all solved issue messages: Transactions with solved issues - Returns list with issue messages")
    void getAllSolvedIssueMessages_TransactionsWithSolvedIssues_ReturnsListWithIssueMessages() {
        List<Transaction> transactions = getAllTransactionsWithIssues();
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        List<String> solvedIssueMessages = transactionIssueService.getAllSolvedIssueMessages();

        assertNotNull(solvedIssueMessages);
        assertEquals(2, solvedIssueMessages.size());
        assertTrue(solvedIssueMessages.contains("No issues"));
        assertTrue(solvedIssueMessages.contains("Suspicious activity"));
    }

    private List<Transaction> getAllTransactions() {
        return new ArrayList<>(List.of(
                new Transaction(
                        1284564,
                        150.2,
                        "Tom Shelby",
                        22,
                        "Arthur Shelby",
                        60,
                        3,
                        false,
                        "Looks like money laundering"
                ),
                new Transaction(
                        1284565,
                        150.2,
                        "Tom Shelby",
                        22,
                        "John Doe",
                        40,
                        5,
                        false,
                        "Suspicious activity"
                ),
                new Transaction(
                        1284566,
                        150.2,
                        "Tom Shelby",
                        22,
                        "Jane Smith",
                        35,
                        7,
                        false,
                        "No issues"
                )
        ));
    }

    private List<Transaction> getAllTransactionsWithIssues() {
        return new ArrayList<>(List.of(
                new Transaction(
                        1284564,
                        150.2,
                        "Tom Shelby",
                        22,
                        "Arthur Shelby",
                        60,
                        3,
                        false,
                        "Looks like money laundering"
                ),
                new Transaction(
                        1284565,
                        150.2,
                        "Tom Shelby",
                        22,
                        "John Doe",
                        40,
                        5,
                        true,
                        "Suspicious activity"
                ),
                new Transaction(
                        1284566,
                        150.2,
                        "Tom Shelby",
                        22,
                        "Jane Smith",
                        35,
                        7,
                        true,
                        "No issues"
                )
        ));
    }
}
