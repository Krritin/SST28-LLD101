package com.example.payments;

import java.util.Objects;

public class FastPayAdapter implements PaymentGateway {

    private final FastPayClient fastPay;

    public FastPayAdapter(FastPayClient fastPay) {
        this.fastPay = Objects.requireNonNull(fastPay, "FastPayClient cannot be null");
    }

    @Override
    public String processPayment(String userId, int totalInPaise) {
        return fastPay.payNow(userId, totalInPaise);
    }
}
