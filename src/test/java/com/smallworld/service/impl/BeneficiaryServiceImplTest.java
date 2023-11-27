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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BeneficiaryServiceImplTest {
    @Mock
    private TransactionService transactionService;

    private BeneficiaryServiceImpl beneficiaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        beneficiaryService = new BeneficiaryServiceImpl(transactionService);
    }

    @Test
    @DisplayName("Get transactions by beneficiary name: No transactions - Returns empty map")
    void getTransactionsByBeneficiaryName_NoTransactions_ReturnsEmptyMap() {
        when(transactionService.getAllTransactions()).thenReturn(Collections.emptyList());

        Map<String, Transaction> transactionsByBeneficiary = beneficiaryService.getTransactionsByBeneficiaryName();

        assertNotNull(transactionsByBeneficiary);
        assertTrue(transactionsByBeneficiary.isEmpty());
    }

    @Test
    @DisplayName("Get transactions by beneficiary name: Transactions exist - Returns map with correct mappings")
    void getTransactionsByBeneficiaryName_TransactionsExist_ReturnsMapWithCorrectMappings() {
        List<Transaction> transactions = getAllTransactions();
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        Map<String, Transaction> transactionsByBeneficiary = beneficiaryService.getTransactionsByBeneficiaryName();

        assertNotNull(transactionsByBeneficiary);
        assertEquals(3, transactionsByBeneficiary.size());
        assertTrue(transactionsByBeneficiary.containsKey("Arthur Shelby"));
        assertEquals(transactions.get(0), transactionsByBeneficiary.get("Arthur Shelby"));
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
                        true,
                        "No issues"
                )
        ));
    }
}
