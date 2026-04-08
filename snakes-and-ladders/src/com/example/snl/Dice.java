package com.example.snl;

import java.util.Random;

public class Dice {

    private final Random rng = new Random();

    public int roll() {
        return rng.nextInt(6) + 1;
    }
}
