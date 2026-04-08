package com.example.snl;

import java.util.*;

public class Board {

    private final int totalCells;
    private final Map<Integer, Integer> snakes = new HashMap<>();
    private final Map<Integer, Integer> ladders = new HashMap<>();

    public Board(int side, DifficultyLevel lvl) {
        this.totalCells = side * side;
        generate(side, lvl);
    }

    private void generate(int side, DifficultyLevel lvl) {
        Random rng = new Random();
        Set<Integer> reserved = new HashSet<>(Arrays.asList(1, totalCells));

        // place snakes
        for (int i = 0; i < side; i++) {
            int mouth = pickFree(rng, reserved);
            reserved.add(mouth);

            int tail;
            if (lvl == DifficultyLevel.BRUTAL) {
                tail = rng.nextInt(Math.max(1, mouth / 2)) + 1;
            } else {
                tail = mouth - rng.nextInt(Math.max(1, mouth / 3)) - 1;
                if (tail < 1) tail = 1;
            }
            if (reserved.contains(tail)) tail = Math.max(1, tail - 1);
            reserved.add(tail);
            snakes.put(mouth, tail);
        }

        // place ladders
        for (int i = 0; i < side; i++) {
            int bottom = pickFree(rng, reserved);
            reserved.add(bottom);

            int top;
            if (lvl == DifficultyLevel.NORMAL) {
                top = bottom + rng.nextInt(totalCells - bottom);
                if (top >= totalCells) top = totalCells - 1;
            } else {
                top = bottom + rng.nextInt(Math.max(1, (totalCells - bottom) / 2)) + 1;
                if (top >= totalCells) top = totalCells - 1;
            }
            if (reserved.contains(top)) top = Math.min(totalCells - 1, top + 1);
            reserved.add(top);
            ladders.put(bottom, top);
        }
    }

    private int pickFree(Random rng, Set<Integer> used) {
        int cell;
        do { cell = rng.nextInt(totalCells - 2) + 2; } while (used.contains(cell));
        return cell;
    }

    public int getTotalCells() { return totalCells; }

    public int evaluate(int cell) {
        if (snakes.containsKey(cell)) {
            int dest = snakes.get(cell);
            System.out.println("  bitten! " + cell + " -> " + dest);
            return dest;
        }
        if (ladders.containsKey(cell)) {
            int dest = ladders.get(cell);
            System.out.println("  climbed! " + cell + " -> " + dest);
            return dest;
        }
        return cell;
    }

    public void showLayout() {
        System.out.println("Snakes : " + snakes);
        System.out.println("Ladders: " + ladders);
    }
}
