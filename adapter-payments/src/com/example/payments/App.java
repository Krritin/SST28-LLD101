package com.example.payments;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, PaymentGateway> providers = new HashMap<>();
        providers.put("fast_pay", new FastPayAdapter(new FastPayClient()));
        providers.put("safe_cash", new SafeCashAdapter(new SafeCashClient()));

        OrderService orderService = new OrderService(providers);

        String txn1 = orderService.placeOrder("fast_pay", "U101", 2499);
        String txn2 = orderService.placeOrder("safe_cash", "U202", 4999);

        System.out.println("Transaction 1: " + txn1);
        System.out.println("Transaction 2: " + txn2);

        // verify unknown provider throws
        try {
            orderService.placeOrder("unknown_gw", "U303", 100);
        } catch (IllegalArgumentException ex) {
            System.out.println("Caught: " + ex.getMessage());
        }
    }
}
