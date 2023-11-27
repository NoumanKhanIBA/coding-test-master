package com.smallworld.service;

import com.smallworld.entity.Transaction;

import java.util.Map;

public interface BeneficiaryService {
    Map<String, Transaction> getTransactionsByBeneficiaryName();
}