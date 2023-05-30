package com.example.webapiassessment.controller;

import com.example.webapiassessment.domain.Customer;
import com.example.webapiassessment.domain.Transaction;
import com.example.webapiassessment.dto.CustomerRewardsDTO;
import com.example.webapiassessment.dto.MonthlyRewardDTO;
import com.example.webapiassessment.service.CustomerService;
import com.example.webapiassessment.service.TransactionService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.temporal.TemporalAdjusters;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {
    private final CustomerService customerService;
    private final TransactionService transactionService;

    public RewardController(CustomerService customerService, TransactionService transactionService) {
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    /**
     * Get the most recent 3 months rewards for a specific customer by their ID.
     *
     * @param customerId The ID of the customer.
     * @return The object containing monthly rewards and total rewards for the customer.
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerRewardsDTO> getRewardsByCustomerId(@PathVariable Long customerId) throws ChangeSetPersister.NotFoundException {

        Customer customer = customerService.getCustomerById(customerId);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        // Get transactions for the customer within the past three months
        LocalDate endDate = LocalDate.now().plusDays(1L);
        LocalDate startDate = LocalDate.now().minusMonths(2).with(TemporalAdjusters.firstDayOfMonth()).minusDays(1L);
        List<Transaction> transactions = transactionService.getTransactionsByCustomerIdAndDateRange(customerId, startDate, endDate);

        // Calculate rewards per month and total rewards
        List<MonthlyRewardDTO> monthlyRewards = calculateMonthlyRewards(transactions);
        int totalRewards = calculateTotalRewards(monthlyRewards);

        // Create the CustomerRewardsDTO object
        CustomerRewardsDTO customerRewardsDTO = new CustomerRewardsDTO(monthlyRewards, totalRewards);

        return ResponseEntity.ok(customerRewardsDTO);
    }

    /**
     * Calculate reward points based on the given transaction.
     *
     * @param amount The amount of this transaction.
     * @return The reward points.
     */
    private int calculateRewardPoints(double amount) {
        int rewardPoints = 0;

        if (amount > 100) {
            rewardPoints += (int) (amount - 100);
        }
        if (amount > 50) {
            rewardPoints += (int) (amount - 50);
        }

        return rewardPoints;
    }

    /**
     * Calculate monthly rewards based on the given list of transactions.
     *
     * @param transactions The list of transactions in 3 months.
     * @return The list of MonthlyRewardDTO objects.
     */
    List<MonthlyRewardDTO> calculateMonthlyRewards(List<Transaction> transactions) {
        Map<String, Integer> monthlyRewards = new HashMap<>();

        // Calculate rewards for each transaction and add them by month
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            String monthKey = transactionDate.getMonth().toString() + " " + transactionDate.getYear();
            int rewardPoints = this.calculateRewardPoints(transaction.getAmount());
            monthlyRewards.put(monthKey, monthlyRewards.getOrDefault(monthKey,0) + rewardPoints);
        }

        List<MonthlyRewardDTO> monthlyRewardsReturn = new ArrayList<>();

        // Store rewards for each month as MonthlyRewardDTO object
        for (Map.Entry<String, Integer> entry : monthlyRewards.entrySet()) {
            String monthKey = entry.getKey();
            int monthlyReward = entry.getValue();

            MonthlyRewardDTO monthlyRewardDTO = new MonthlyRewardDTO(monthKey, monthlyReward);
            monthlyRewardsReturn.add(monthlyRewardDTO);
        }

        return monthlyRewardsReturn;
    }

    /**
     * Calculate the total rewards based on the given list of monthly rewards.
     *
     * @param monthlyRewards The list of MonthlyRewardDTO objects.
     * @return The total rewards.
     */
    int calculateTotalRewards(List<MonthlyRewardDTO> monthlyRewards) {
        int rewardPoints = 0;
        for (MonthlyRewardDTO monthlyReward: monthlyRewards) {
            rewardPoints += monthlyReward.getPoint();
        }
        return rewardPoints;
    }
}
