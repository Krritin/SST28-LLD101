package com.example.payments;

public class SafeCashPayment {

    private final int paise;
    private final String userId;

    public SafeCashPayment(int paise, String userId) {
        this.paise = paise;
        this.userId = userId;
    }

    public String execute() {
        return "SAFE-TXN(" + userId + "," + paise + ")";
    }
}
