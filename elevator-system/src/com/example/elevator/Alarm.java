package com.example.elevator;

public class Alarm {

    private boolean active;

    public void activate()   { active = true; }
    public void deactivate() { active = false; }
    public boolean isActive(){ return active; }
}
