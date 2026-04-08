package com.example.snl;

public class Player {

    private final String tag;
    private int cell;
    private boolean finished;

    public Player(String tag) {
        this.tag = tag;
        this.cell = 0;
        this.finished = false;
    }

    public String getTag()    { return tag; }
    public int getCell()      { return cell; }
    public void setCell(int c){ this.cell = c; }
    public boolean isFinished()         { return finished; }
    public void markFinished(boolean f) { this.finished = f; }
}
