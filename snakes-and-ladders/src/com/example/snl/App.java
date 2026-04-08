package com.example.snl;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Board side length (n for n x n): ");
        int side = sc.nextInt();

        System.out.print("How many players? ");
        int numPlayers = sc.nextInt();

        System.out.print("Difficulty (normal / brutal): ");
        String d = sc.next();
        DifficultyLevel lvl = d.equalsIgnoreCase("brutal")
                ? DifficultyLevel.BRUTAL : DifficultyLevel.NORMAL;

        Game game = new Game(side, numPlayers, lvl);
        game.play();

        sc.close();
    }
}
