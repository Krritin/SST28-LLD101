package com.example.elevator;

public class Floor {

    private final int number;
    private final ExternalButton btnUp;
    private final ExternalButton btnDown;
    private boolean maintenance;

    public Floor(int number) {
        this.number = number;
        this.btnUp   = new ExternalButton(number, Direction.UP);
        this.btnDown = new ExternalButton(number, Direction.DOWN);
        this.maintenance = false;
    }

    public void callUp(ElevatorController ctrl)   { if (!maintenance) btnUp.press(ctrl); }
    public void callDown(ElevatorController ctrl)  { if (!maintenance) btnDown.press(ctrl); }

    public void setMaintenance(boolean flag) { this.maintenance = flag; }
    public boolean isMaintenance()           { return maintenance; }
    public int getNumber()                   { return number; }
}
