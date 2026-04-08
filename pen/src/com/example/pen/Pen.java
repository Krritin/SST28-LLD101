package com.example.pen;

public abstract class Pen {

    private Refill refill;
    private boolean ready;

    public Pen(Refill refill) {
        this.refill = refill;
        this.ready = false;
    }

    public void write(String text) {
        if (!ready) {
            System.out.println("pen is closed");
            return;
        }
        if (refill == null || refill.isDry()) {
            System.out.println("out of ink");
            return;
        }
        System.out.println("writing '" + text + "' with " + refill.getColour() + " ink");
        refill.consume(0.1);
    }

    public abstract void open();
    public abstract void shut();

    public void changeRefill(Refill newRefill) {
        this.refill = newRefill;
        System.out.println("new refill: " + newRefill.getColour());
    }

    public Refill getRefill() { return refill; }

    protected void setReady(boolean flag) { this.ready = flag; }
    protected boolean isReady()           { return ready; }
}
