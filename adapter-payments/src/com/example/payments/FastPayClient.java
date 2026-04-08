package com.example.payments;

public class FastPayClient {
    public String payNow(String userId, int paise) {
        return "FAST-" + userId + "-" + paise;
    }
}
