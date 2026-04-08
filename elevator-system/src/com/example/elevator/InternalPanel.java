package com.example.elevator;

import java.util.HashMap;
import java.util.Map;

public class InternalPanel {

    private final Map<Integer, InternalButton> buttons = new HashMap<>();
    private final ElevatorCart cart;

    public InternalPanel(ElevatorCart cart, int floors) {
        this.cart = cart;
        for (int f = 0; f < floors; f++) {
            buttons.put(f, new InternalButton(f));
        }
    }

    public void selectFloor(int floor) {
        InternalButton btn = buttons.get(floor);
        if (btn != null) btn.press(cart);
    }

    public void openDoor()      { cart.openDoor(); }
    public void closeDoor()     { cart.closeDoor(); }
    public void hitEmergency()  { cart.handleEmergency(); }
}
