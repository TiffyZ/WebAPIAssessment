package com.example.webapiassessment.service;

import com.example.webapiassessment.domain.Customer;
import com.example.webapiassessment.domain.Transaction;
import com.example.webapiassessment.repository.TransactionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    private TransactionService transactionService;
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionService = new TransactionService(transactionRepository, customerService);
    }

    @Test
    public void testGetTransactionsByCustomerId() {
        long customerId = 1L;
        Customer customer = new Customer(customerId);
        Transaction transaction1 = new Transaction(customer, LocalDate.of(2023, 5, 1), 100.0);
        Transaction transaction2 = new Transaction(customer, LocalDate.of(2023, 5, 15), 50.0);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(transactions);

        // Act
        List<Transaction> result = transactionService.getTransactionsByCustomerId(customerId);

        // Assert
        assertEquals(transactions, result);
        verify(transactionRepository, times(1)).findByCustomerId(customerId);
    }
}
