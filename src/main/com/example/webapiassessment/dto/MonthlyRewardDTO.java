package com.example.webapiassessment.dto;

public class MonthlyRewardDTO {
    private String month;
    private int point;

    public MonthlyRewardDTO(String month, int point) {
        this.month = month;
        this.point = point;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getPoint() {
        return point;
    }

    public void setAmount(int point) {
        this.point = point;
    }
}
