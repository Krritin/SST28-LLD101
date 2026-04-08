package com.example.elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElevatorCart {

    private final int id;
    private int currentFloor;
    private ElevatorState state;
    private final Door door;
    private final Alarm alarm;
    private final Display display;
    private final WeightSensor sensor;
    private final InternalPanel panel;
    private final List<Integer> queue = new ArrayList<>();

    public ElevatorCart(int id, double weightLimit, int floors) {
        this.id = id;
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.door = new Door();
        this.alarm = new Alarm();
        this.display = new Display();
        this.sensor = new WeightSensor(weightLimit);
        this.panel = new InternalPanel(this, floors);
    }

    public void enqueueFloor(int floor) {
        if (state == ElevatorState.OUT_OF_SERVICE) return;
        if (!queue.contains(floor)) queue.add(floor);
    }

    public void step() {
        if (state == ElevatorState.OUT_OF_SERVICE) return;

        if (sensor.isExceeded()) {
            door.open();
            alarm.activate();
            state = ElevatorState.IDLE;
            return;
        }
        if (queue.isEmpty()) {
            state = ElevatorState.IDLE;
            return;
        }

        Collections.sort(queue);
        int target = queue.get(0);

        if (target > currentFloor) state = ElevatorState.MOVING_UP;
        else if (target < currentFloor) state = ElevatorState.MOVING_DOWN;

        while (currentFloor != target) {
            currentFloor += (state == ElevatorState.MOVING_UP) ? 1 : -1;
            display.refresh(currentFloor,
                    state == ElevatorState.MOVING_UP ? Direction.UP : Direction.DOWN);
        }

        door.open();
        door.close();
        queue.remove(Integer.valueOf(target));
        if (queue.isEmpty()) state = ElevatorState.IDLE;
    }

    public void serveAllRequests() {
        while (!queue.isEmpty()) {
            if (sensor.isExceeded()) { door.open(); alarm.activate(); return; }
            step();
        }
        state = ElevatorState.IDLE;
    }

    public void openDoor()  { door.open(); }
    public void closeDoor() { door.close(); }

    public void handleEmergency() {
        state = ElevatorState.IDLE;
        alarm.activate();
        door.open();
    }

    public void setWeight(double kg)  { sensor.setCurrent(kg); }
    public void enterMaintenance()    { state = ElevatorState.OUT_OF_SERVICE; }
    public void exitMaintenance()     { state = ElevatorState.IDLE; }

    public int getId()                { return id; }
    public int getCurrentFloor()      { return currentFloor; }
    public ElevatorState getState()   { return state; }
    public InternalPanel getPanel()   { return panel; }
    public boolean isOverweight()     { return sensor.isExceeded(); }
    public boolean isAlarmOn()        { return alarm.isActive(); }
    public boolean isDoorOpen()       { return door.isOpen(); }
}
