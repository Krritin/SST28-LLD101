package com.example.booking;

public class SlotPricingRule implements PricingRule {

    @Override
    public int apply(int base, Show show) {
        String t = show.getSlot().toUpperCase();
        if (t.contains("6:00PM") || t.contains("7:00PM") || t.contains("9:00PM"))
            return base + (int)(base * 0.3);     // evening surge
        if (t.contains("10:00AM") || t.contains("11:00AM"))
            return base - 25;                     // morning discount
        return base;
    }
}
