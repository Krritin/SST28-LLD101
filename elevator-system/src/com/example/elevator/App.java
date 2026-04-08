package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        int numFloors = 10;

        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i < numFloors; i++) floors.add(new Floor(i));

        List<ElevatorCart> lifts = new ArrayList<>();
        lifts.add(new ElevatorCart(1, 650, numFloors));
        lifts.add(new ElevatorCart(2, 450, numFloors));
        lifts.add(new ElevatorCart(3, 750, numFloors));

        ElevatorController ctrl = new ElevatorController(lifts, floors);

        // basic up request
        floors.get(5).callUp(ctrl);
        lifts.get(0).serveAllRequests();
        System.out.println("Lift-1 floor: " + lifts.get(0).getCurrentFloor()
                + " state: " + lifts.get(0).getState());

        // internal floor press
        lifts.get(0).getPanel().selectFloor(8);
        lifts.get(0).serveAllRequests();
        System.out.println("Lift-1 floor: " + lifts.get(0).getCurrentFloor()
                + " state: " + lifts.get(0).getState());

        // door test
        lifts.get(0).getPanel().openDoor();
        System.out.println("Door open? " + lifts.get(0).isDoorOpen());
        lifts.get(0).getPanel().closeDoor();
        System.out.println("Door open? " + lifts.get(0).isDoorOpen());

        // overweight
        lifts.get(1).setWeight(500);
        lifts.get(1).getPanel().selectFloor(3);
        lifts.get(1).step();
        System.out.println("Overweight => alarm=" + lifts.get(1).isAlarmOn()
                + " door=" + lifts.get(1).isDoorOpen());

        // emergency
        lifts.get(2).getPanel().selectFloor(4);
        lifts.get(2).getPanel().hitEmergency();
        System.out.println("Emergency => alarm=" + lifts.get(2).isAlarmOn()
                + " state=" + lifts.get(2).getState());

        // floor maintenance
        ctrl.markFloorMaintenance(3, true);
        floors.get(3).callUp(ctrl);
        System.out.println("Floor-3 maintenance => Lift-1 still at: " + lifts.get(0).getCurrentFloor());

        // cart maintenance
        ctrl.markCartMaintenance(2);
        lifts.get(1).getPanel().selectFloor(5);
        lifts.get(1).setWeight(150);
        lifts.get(1).step();
        System.out.println("Lift-2 maintenance => state=" + lifts.get(1).getState());

        ctrl.clearCartMaintenance(2);
        lifts.get(1).getPanel().selectFloor(5);
        lifts.get(1).serveAllRequests();
        System.out.println("Lift-2 restored => floor=" + lifts.get(1).getCurrentFloor());

        // multiple external requests
        floors.get(1).callUp(ctrl);
        floors.get(9).callDown(ctrl);
        for (ElevatorCart c : lifts) c.serveAllRequests();
        System.out.println("Final => L1:" + lifts.get(0).getCurrentFloor()
                + " L2:" + lifts.get(1).getCurrentFloor()
                + " L3:" + lifts.get(2).getCurrentFloor());
    }
}
