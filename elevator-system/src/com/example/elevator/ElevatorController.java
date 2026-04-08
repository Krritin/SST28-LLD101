package com.example.elevator;

import java.util.List;

public class ElevatorController {

    private final List<ElevatorCart> carts;
    private final List<Floor> floors;

    public ElevatorController(List<ElevatorCart> carts, List<Floor> floors) {
        this.carts = carts;
        this.floors = floors;
    }

    public void dispatchRequest(int floor, Direction dir) {
        // skip if floor is under maintenance
        for (Floor f : floors) {
            if (f.getNumber() == floor && f.isMaintenance()) return;
        }

        ElevatorCart chosen = null;
        int closest = Integer.MAX_VALUE;

        for (ElevatorCart cart : carts) {
            if (cart.getState() == ElevatorState.OUT_OF_SERVICE) continue;
            int gap = Math.abs(cart.getCurrentFloor() - floor);

            if (cart.getState() == ElevatorState.IDLE && gap < closest) {
                closest = gap;
                chosen = cart;
            } else if (cart.getState() == ElevatorState.MOVING_UP
                    && dir == Direction.UP && cart.getCurrentFloor() < floor && gap < closest) {
                closest = gap;
                chosen = cart;
            } else if (cart.getState() == ElevatorState.MOVING_DOWN
                    && dir == Direction.DOWN && cart.getCurrentFloor() > floor && gap < closest) {
                closest = gap;
                chosen = cart;
            }
        }

        // fallback: pick first non-maintenance cart
        if (chosen == null) {
            for (ElevatorCart cart : carts) {
                if (cart.getState() != ElevatorState.OUT_OF_SERVICE) { chosen = cart; break; }
            }
        }

        if (chosen != null) chosen.enqueueFloor(floor);
    }

    public void markFloorMaintenance(int floorNum, boolean flag) {
        for (Floor f : floors) {
            if (f.getNumber() == floorNum) { f.setMaintenance(flag); return; }
        }
    }

    public void markCartMaintenance(int cartId) {
        for (ElevatorCart c : carts) {
            if (c.getId() == cartId) { c.enterMaintenance(); return; }
        }
    }

    public void clearCartMaintenance(int cartId) {
        for (ElevatorCart c : carts) {
            if (c.getId() == cartId) { c.exitMaintenance(); return; }
        }
    }
}
