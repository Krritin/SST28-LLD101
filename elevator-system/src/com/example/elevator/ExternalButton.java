package com.example.elevator;

public class ExternalButton {

    private final int atFloor;
    private final Direction dir;

    public ExternalButton(int atFloor, Direction dir) {
        this.atFloor = atFloor;
        this.dir = dir;
    }

    public int getAtFloor()    { return atFloor; }
    public Direction getDir()  { return dir; }

    public void press(ElevatorController ctrl) {
        ctrl.dispatchRequest(atFloor, dir);
    }
}
