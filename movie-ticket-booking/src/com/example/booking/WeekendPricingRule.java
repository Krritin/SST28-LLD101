package com.example.booking;

import java.time.LocalDate;

public class WeekendPricingRule implements PricingRule {

    @Override
    public int apply(int base, Show show) {
        int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
        boolean isWeekend = (dayOfWeek == 6 || dayOfWeek == 7);
        return isWeekend ? (int)(base * 1.2) : base;
    }
}
