package com.smallworld.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface TransactionIssueService {
    Set<Integer> getUnsolvedIssueIds() throws IOException;

    List<String> getAllSolvedIssueMessages();
}

