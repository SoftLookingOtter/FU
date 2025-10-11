package se.saralinden.treirad;

import java.util.Scanner;

// Game.java
public class Game {

    private final Scanner in = new Scanner(System.in); // private final = safe reuse
    // private: only Game class can access this field
    // final: the reference to "in" cannot be changed after assignment

    public void start() {
        System.out.println();
        System.out.println("🦝 Raccoon Referee: Welcome to Tic-Tac-Toe!");
        System.out.println("Marks: ❌ vs ⭕. Choose cells 1–9. Ctrl+C to quit.");

        Board board = new Board(); // create the board
        board.print();             // show it once

        System.out.print("> "); // prompt for player input

        // ...show board, ask names, etc.
    }
}

