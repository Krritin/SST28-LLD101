package com.example.pen;

public class CapPen extends Pen {

    private boolean capped;

    public CapPen(Refill refill) {
        super(refill);
        this.capped = true;
    }

    @Override
    public void open() {
        capped = false;
        setReady(true);
        System.out.println("cap off");
    }

    @Override
    public void shut() {
        capped = true;
        setReady(false);
        System.out.println("cap on");
    }
}
