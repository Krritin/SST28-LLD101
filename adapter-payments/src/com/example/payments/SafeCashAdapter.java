package com.example.payments;

import java.util.Objects;

public class SafeCashAdapter implements PaymentGateway {

    private final SafeCashClient safeCash;

    public SafeCashAdapter(SafeCashClient safeCash) {
        this.safeCash = Objects.requireNonNull(safeCash, "SafeCashClient cannot be null");
    }

    @Override
    public String processPayment(String userId, int totalInPaise) {
        SafeCashPayment txn = safeCash.initiatePayment(totalInPaise, userId);
        return txn.execute();
    }
}
