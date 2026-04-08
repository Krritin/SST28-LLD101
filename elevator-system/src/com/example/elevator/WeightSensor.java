package com.example.elevator;

public class WeightSensor {

    private final double limit;
    private double current;

    public WeightSensor(double limit) {
        this.limit = limit;
        this.current = 0;
    }

    public void setCurrent(double kg) { this.current = kg; }
    public double getCurrent()        { return current; }
    public double getLimit()          { return limit; }
    public boolean isExceeded()       { return current > limit; }
}
