package com.example.elevator;

public class Display {

    private int currentFloor;
    private Direction dir;

    public void refresh(int floor, Direction dir) {
        this.currentFloor = floor;
        this.dir = dir;
    }

    public int getCurrentFloor() { return currentFloor; }
    public Direction getDir()    { return dir; }
}
