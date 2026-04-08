package com.example.payments;

public class SafeCashClient {
    public SafeCashPayment initiatePayment(int paise, String userId) {
        return new SafeCashPayment(paise, userId);
    }
}
