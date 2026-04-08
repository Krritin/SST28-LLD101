package com.example.payments;

import java.util.Map;
import java.util.Objects;

public class OrderService {

    private final Map<String, PaymentGateway> providerMap;

    public OrderService(Map<String, PaymentGateway> providerMap) {
        this.providerMap = Objects.requireNonNull(providerMap, "provider map required");
    }

    public String placeOrder(String providerName, String userId, int totalInPaise) {
        Objects.requireNonNull(providerName, "provider name required");
        PaymentGateway gateway = providerMap.get(providerName);
        if (gateway == null) {
            throw new IllegalArgumentException("No such provider: " + providerName);
        }
        return gateway.processPayment(userId, totalInPaise);
    }
}
