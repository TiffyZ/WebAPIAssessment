package com.example.webapiassessment.dto;

import java.util.List;

public class CustomerRewardsDTO {
    private List<MonthlyRewardDTO> monthlyRewards;
    private int totalRewards;

    public CustomerRewardsDTO(List<MonthlyRewardDTO> monthlyRewards, int totalRewards) {
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }

    /**
     * Get the list of monthly rewards for the customer.
     *
     * @return The list of MonthlyRewardDTO objects representing the monthly rewards.
     */
    public List<MonthlyRewardDTO> getMonthlyRewards() {
        return monthlyRewards;
    }

    /**
     * Set the list of monthly rewards for the customer.
     *
     * @param monthlyRewards The list of MonthlyRewardDTO objects representing the monthly rewards.
     */
    public void setMonthlyRewards(List<MonthlyRewardDTO> monthlyRewards) {
        this.monthlyRewards = monthlyRewards;
    }

    /**
     * Get the total rewards for the customer.
     *
     * @return The total rewards earned by the customer.
     */
    public int getTotalRewards() {
        return totalRewards;
    }

    /**
     * Set the total rewards for the customer.
     *
     * @param totalRewards The total rewards earned by the customer.
     */
    public void setTotalRewards(int totalRewards) {
        this.totalRewards = totalRewards;
    }
}
