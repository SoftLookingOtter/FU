package se.saralinden.treirad;

// Game.java
public class Game {
    public void start() {
        System.out.println("🦝 Raccoon Referee: Welcome to Tic-Tac-Toe!");
        System.out.println("Marks: ❌ vs ⭕. Choose cells 1–9. Ctrl+C to quit.");

        Board board = new Board(); // create the board
        board.print();             // show it once

        System.out.print("> "); // prompt for player input

        // ...show board, ask names, etc.
    }
}

