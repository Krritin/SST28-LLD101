package com.example.booking;

public class Seat {

    private final String label;
    private final SeatType type;
    private final int basePrice;

    public Seat(String label, SeatType type, int basePrice) {
        this.label = label;
        this.type = type;
        this.basePrice = basePrice;
    }

    public String getLabel()    { return label; }
    public SeatType getType()   { return type; }
    public int getBasePrice()   { return basePrice; }

    @Override
    public String toString() { return label + "(" + type + " Rs" + basePrice + ")"; }
}
