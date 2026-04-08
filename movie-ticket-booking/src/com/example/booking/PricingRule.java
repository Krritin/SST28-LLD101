package com.example.booking;

public interface PricingRule {
    int apply(int base, Show show);
}
