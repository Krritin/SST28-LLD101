package com.example.parking;

public class ParkingSlot {

    private final int number;
    private final SlotType type;
    private final int level;
    private final int proximity;     // distance from nearest gate
    private boolean taken;

    public ParkingSlot(int number, SlotType type, int level, int proximity) {
        this.number = number;
        this.type = type;
        this.level = level;
        this.proximity = proximity;
        this.taken = false;
    }

    public int getNumber()      { return number; }
    public SlotType getType()   { return type; }
    public int getLevel()       { return level; }
    public int getProximity()   { return proximity; }
    public boolean isTaken()    { return taken; }

    public void markTaken()     { taken = true; }
    public void markFree()      { taken = false; }
}
