package se.saralinden.treirad; // package declaration

import java.util.Scanner;

/**
 * Game loop.
 * - Owns the game state (grid data) and manages turn order.
 * - Reads validated input and updates the board.
 * - Uses Board to render and to check win/draw.
 * Helpers in this class: readCell1to9(), readFreeCell1to9(), placeMark().
 */

public class Game {

    private final Scanner in = new Scanner(System.in); // private final = safe reuse
    // private: only Game class can access this field
    // final: the reference to "in" cannot be reassigned to another Scanner object later

    public void start() {
        System.out.println();
        System.out.println("🦝 Raccoon Referee: Welcome to Tic-Tac-Toe!");
        System.out.println("Marks: ❌ vs ⭕. Choose cells 1–9. Ctrl+C to quit.");

        //noinspection InfiniteLoopStatement
        while (true) { // repeat forever (until user interrupts with Ctrl+C)
            playOneGame();                 // plays one game (win/draw)
            System.out.println("Starting a new game...\n");
        }
    }

    // --- Game state ---
    private void playOneGame() {
        String[] gameGrid = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};     // Passed to Board for printing.
        Board board = new Board();                     // Create a new Board instance to use its methods.

        String current = "X"; // X starts
        while (true) {
            board.print(gameGrid);
            System.out.println("Player " + current + ", pick a cell (1–9):");

            int cell = readFreeCell1to9(gameGrid); // using helpers below
            placeMark(gameGrid, cell, current); // update game state with the player's mark by calling the helper method

            if (board.isWin(gameGrid, current)) {
                board.print(gameGrid);
                System.out.println("🎉 Player " + current + " wins!");
                return; // back to start() → new game
            }

            if (board.isDraw(gameGrid)) {
                board.print(gameGrid);
                System.out.println("🤝 It's a draw!");
                return; // back to start() → new game
            }

            current = current.equals("X") ? "O" : "X"; // switch player
        }
    }

    // --- HELPERS ---

    // --- User input ---
    // 1: read a number 1–9
    private int readCell1to9() {
        while (true) { // repeat until we return a valid number
            System.out.print("> "); // prompt for input
            String s = in.nextLine().trim(); // read a line and trim whitespace

            try {
                int n = Integer.parseInt(s); // Try to convert the user's input (String) into an integer value (may throw NumberFormatException if input is not a valid integer)
                if (n >= 1 && n <= 9) return n; // if it's between 1 and 9, return it
            } catch (NumberFormatException ignored) {
            } // if conversion failed, ignore and continue to the next line (i.e., print the error message below) and then loop again from the top to ask for input again

            System.out.println("Please type a number between 1 and 9."); // if we get here, input was invalid
        }
    }

    // 2: keep asking until the chosen cell is still showing a number (= free)
    private int readFreeCell1to9(String[] grid) {

        while (true) {
            int n = readCell1to9(); // call the method above (1.) to get a number 1–9
            if (grid[n - 1].equals(String.valueOf(n)))
                return n; // String.valueOf(n) converts int n to String, so we can compare it to grid[n - 1] (which is a String) // if the cell still shows a number, it's free -> return it

            System.out.println("That cell is taken. Pick a free one."); // if we get here, cell was taken
        }
    }

    // --- Game state update ---
    // 3: place a mark at a 1–9 cell
    private void placeMark(String[] grid, int cell1to9, String mark) {
        grid[cell1to9 - 1] = mark; // grid array points to the same memory as gameGrid array in start() -> so this changes gameGrid too
    }

}