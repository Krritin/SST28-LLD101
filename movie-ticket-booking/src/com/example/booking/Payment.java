package com.example.booking;

public class Payment {

    private final String txnId;
    private final int amount;
    private final PaymentMode mode;
    private boolean refunded;

    public Payment(String txnId, int amount, PaymentMode mode) {
        this.txnId = txnId;
        this.amount = amount;
        this.mode = mode;
        this.refunded = false;
    }

    public void refund() { refunded = true; }

    public String getTxnId()      { return txnId; }
    public int getAmount()        { return amount; }
    public PaymentMode getMode()  { return mode; }
    public boolean isRefunded()   { return refunded; }

    @Override
    public String toString() {
        return txnId + " Rs" + amount + " " + mode + (refunded ? " REFUNDED" : "");
    }
}
