package com.example.snl;

public class Snake {

    private final int mouth;
    private final int tail;

    public Snake(int mouth, int tail) {
        this.mouth = mouth;
        this.tail = tail;
    }

    public int getMouth() { return mouth; }
    public int getTail()  { return tail; }
}
