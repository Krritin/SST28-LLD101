package com.example.elevator;

public class InternalButton {

    private final int floor;

    public InternalButton(int floor) { this.floor = floor; }

    public int getFloor() { return floor; }

    public void press(ElevatorCart cart) {
        cart.enqueueFloor(floor);
    }
}
