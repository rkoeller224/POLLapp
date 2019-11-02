package com.apress.dto;

public class OptionCount {
    private Long optionId;
    private int count;

    public Long getOptionId() {
        return optionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }
}
