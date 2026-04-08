package com.example.pen;

public class Refill {

    private final String colour;
    private double ink;

    public Refill(String colour) {
        this.colour = colour;
        this.ink = 1.0;
    }

    public String getColour() { return colour; }
    public double getInk()    { return ink; }
    public boolean isDry()    { return ink <= 0; }

    public void consume(double amt) {
        ink = Math.max(0, ink - amt);
    }
}
