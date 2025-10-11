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

        // The game’s 3x3 board data. Passed to Board for printing.
        String[] gameGrid = { "1","2","3","4","5","6","7","8","9" };

        Board board = new Board(); // create a Board object
        board.print(gameGrid); // here we pass the array to print() method in Board class


        // ...show board, ask names, etc.
    }
}

