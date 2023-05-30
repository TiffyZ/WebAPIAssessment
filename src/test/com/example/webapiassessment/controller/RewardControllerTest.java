package com.example.webapiassessment.controller;

import com.example.webapiassessment.domain.Customer;
import com.example.webapiassessment.domain.Transaction;
import com.example.webapiassessment.dto.CustomerRewardsDTO;
import com.example.webapiassessment.service.CustomerService;
import com.example.webapiassessment.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class RewardControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private TransactionService transactionService;
    @InjectMocks
    private RewardController rewardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRewardsByCustomerId() throws Exception {
        Long customerId = 1L;
        Customer customer = new Customer(customerId);

        // Create sample transactions
        Transaction transaction1 = new Transaction(customer, LocalDate.of(2023, 5, 10), 75.0);
        Transaction transaction2 = new Transaction(customer, LocalDate.of(2023, 4, 15), 120.0);
        Transaction transaction3 = new Transaction(customer, LocalDate.of(2023, 3, 20), 50.0);
        Transaction transaction4 = new Transaction(customer, LocalDate.of(2023, 4, 20), 100.0);

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3, transaction4);

        // Mocking
        when(customerService.getCustomerById(customerId)).thenReturn(customer);
        LocalDate endDate = LocalDate.now().plusDays(1L);
        LocalDate startDate = LocalDate.now().minusMonths(2).with(TemporalAdjusters.firstDayOfMonth()).minusDays(1L);
        when(transactionService.getTransactionsByCustomerIdAndDateRange(customerId, startDate, endDate)).thenReturn(transactions);

        // Execute the method
        ResponseEntity<CustomerRewardsDTO> response = rewardController.getRewardsByCustomerId(customerId);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CustomerRewardsDTO customerRewards = response.getBody();
        assertNotNull(customerRewards);
        assertEquals(3, customerRewards.getMonthlyRewards().size());
        assertEquals(165, customerRewards.getTotalRewards());
    }
}
