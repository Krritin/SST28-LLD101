package com.example.payments;

public interface PaymentGateway {
    String processPayment(String userId, int totalInPaise);
}
