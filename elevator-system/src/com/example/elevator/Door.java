package com.example.elevator;

public class Door {

    private boolean isOpen;

    public Door() { this.isOpen = false; }

    public void open()  { if (!isOpen) isOpen = true; }
    public void close() { if (isOpen)  isOpen = false; }
    public boolean isOpen() { return isOpen; }
}
