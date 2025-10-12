package se.saralinden.treirad;

import java.util.Scanner;

/**
 * Game loop.
 * - Owns the game state (grid data) and manages turn order.
 * - Reads validated input and updates the board.
 * - Uses Board to render and to check win/draw.
 * Helpers in this class: askNameForMark(), readCell1to9(who), readFreeCell1to9(grid, who), placeMark().
 */

public class Game {

    private final Scanner in = new Scanner(System.in); // final to prevent reassignment to another Scanner
    private String nameX, nameO; // player names for X and O

    public void start() {
        System.out.println();
        System.out.println("🦝 Raccoon Referee: Welcome to Tic-Tac-Toe!");
        System.out.println("Marks: X vs O. Choose cells 1–9. Ctrl+C to quit.");

        // Ask player names once
        nameX = askNameForMark("X");
        nameO = askNameForMark("O");

        //noinspection InfiniteLoopStatement
        while (true) { // auto-restart after each match
            playOneGame(); // play one match
            System.out.println("Starting a new game...\n");
        }
    }

    // --- NAME HELPER ---
    private String askNameForMark(String mark) {
        while (true) {
            System.out.print("Hi " + mark + "! What's your name? ");
            String s = in.nextLine().trim(); // trim whitespace
            if (!s.isEmpty()) return s;                     // if not empty, return the name
            System.out.println("That was empty — please try again.");
        }
    }

    // --- One game ---
    private void playOneGame() {
        String[] gameGrid = {"1", "2", "3", "4", "5", "6", "7", "8", "9"}; // initial "empty" grid
        Board board = new Board(); // create a Board instance to use its methods

        String current = "X"; // X starts
        while (true) {
            board.print(gameGrid); // render current grid
            String currentName = current.equals("X") ? nameX : nameO; // get current player's name
            System.out.println(currentName + " (" + current + "), your move — pick a cell (1–9):");

            int cell = readFreeCell1to9(gameGrid, currentName); // read a valid, free cell by using helpers below
            placeMark(gameGrid, cell, current); // place the mark on the grid by using helper below

            if (board.isWin(gameGrid, current)) {
                board.print(gameGrid);
                System.out.println("🎉 " + currentName + " (" + current + ") wins!");
                return;
            }

            if (board.isDraw(gameGrid)) {
                board.print(gameGrid);
                System.out.println("🤝 It's a draw!");
                return;
            }

            current = "X".equals(current) ? "O" : "X"; // switch player
        }
    }

    // --- HELPERS: INPUT & STATE UPDATES ---

    // Read 1–9
    private int readCell1to9(String who) {
        while (true) {
            System.out.print("> ");
            String s = in.nextLine().trim();
            try {
                int n = Integer.parseInt(s); // try to parse input as an integer // may throw NumberFormatException if input is not a valid integer
                if (n >= 1 && n <= 9) return n; // if valid number between 1 and 9 (inclusive), return it
            } catch (NumberFormatException ignored) { // ignore invalid input, show message below and reprompt
            }
            System.out.println("Please type a number between 1 and 9, " + who + ".");
        }
    }

    // Ensure the chosen cell is free
    private int readFreeCell1to9(String[] grid, String who) {
        while (true) {
            int n = readCell1to9(who);
            if (grid[n - 1].equals(String.valueOf(n)))
                return n; // if the cell still shows its number, it's free -> return it
            System.out.println("That cell is taken, " + who + ". Pick a free one."); // otherwise, show message and reprompt
        }

    }

    // Place a mark ("X" or "O") at a 1–9 cell
    private void placeMark(String[] grid, int cell1to9, String mark) {
        grid[cell1to9 - 1] = mark; // mutates the provided array to place the mark
    }

}