package com.example.pen;

public class ClickPen extends Pen {

    private boolean extended;

    public ClickPen(Refill refill) {
        super(refill);
        this.extended = false;
    }

    @Override
    public void open() {
        extended = true;
        setReady(true);
        System.out.println("click — nib out");
    }

    @Override
    public void shut() {
        extended = false;
        setReady(false);
        System.out.println("click — nib in");
    }
}
