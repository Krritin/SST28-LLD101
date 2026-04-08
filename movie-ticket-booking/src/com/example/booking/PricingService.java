package com.example.booking;

import java.util.ArrayList;
import java.util.List;

public class PricingService {

    private final List<PricingRule> rules = new ArrayList<>();

    public void addRule(PricingRule rule) { rules.add(rule); }
    public void resetRules()             { rules.clear(); }

    public int computePrice(Seat seat, Show show) {
        int price = seat.getBasePrice();
        for (PricingRule rule : rules) {
            price = rule.apply(price, show);
        }
        return Math.max(price, seat.getBasePrice());   // never below base
    }
}
