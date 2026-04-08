package com.example.snl;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Board board;
    private final Dice dice;
    private final List<Player> players;

    public Game(int side, int numPlayers, DifficultyLevel lvl) {
        this.board = new Board(side, lvl);
        this.dice = new Dice();
        this.players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) players.add(new Player("P" + i));
    }

    public void play() {
        System.out.println("Board: " + board.getTotalCells() + " cells | " + players.size() + " players");
        board.showLayout();
        System.out.println();

        int remaining = players.size();
        int rank = 1;

        while (remaining > 1) {
            for (Player p : players) {
                if (p.isFinished()) continue;

                int roll = dice.roll();
                int next = p.getCell() + roll;

                if (next > board.getTotalCells()) {
                    System.out.println(p.getTag() + " rolled " + roll + " — overshot, stays at " + p.getCell());
                    continue;
                }

                if (next == board.getTotalCells()) {
                    p.setCell(next);
                    p.markFinished(true);
                    remaining--;
                    System.out.println(p.getTag() + " rolled " + roll + " — FINISHED! rank #" + rank);
                    rank++;
                    if (remaining <= 1) break;
                    continue;
                }

                next = board.evaluate(next);
                p.setCell(next);
                System.out.println(p.getTag() + " rolled " + roll + " -> cell " + next);
            }
            System.out.println();
        }

        System.out.println("=== game over ===");
    }
}
