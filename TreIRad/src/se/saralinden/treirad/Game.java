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

    // ANSI colors
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String RESET = "\u001B[0m";
    private final Scanner in = new Scanner(System.in); // final to prevent reassignment to another Scanner
    private String nameX, nameO; // player names for X and O
    private int winsX = 0, winsO = 0, draws = 0;

    // --- QUIT HELPER ---
    // Type 'q'/'quit'/'exit' anywhere to quit
    private void checkQuit(String s) {
        if (s.equalsIgnoreCase("q") || s.equalsIgnoreCase("quit") || s.equalsIgnoreCase("exit")) {
            System.out.println("Goodbye!");
            System.exit(0); // terminate the program with inbuilt method System.exit
        }
    }

    // Map logic mark -> display icon (keep logic X/O)
    private String uiIcon(String mark) {
        return "X".equals(mark) ? (RED + "✖" + RESET) : (BLUE + "○" + RESET);
    }

    public void start() {
        System.out.println();
        System.out.println("🦝 Raccoon Referee: Welcome to Tic-Tac-Toe!");
        System.out.println("Marks: " + uiIcon("X") + " vs " + uiIcon("O") + ". Choose cells 1–9. Type 'q' to quit.");

        // Ask player names once
        nameX = askNameForMark("X");
        nameO = askNameForMark("O");

        //noinspection InfiniteLoopStatement
        while (true) { // auto-restart after each match
            String result = playOneGame(); // "X", "O" eller "D"

            if ("X".equals(result)) winsX++;
            else if ("O".equals(result)) winsO++;
            else draws++;

            // --- Scoreboard ---
            System.out.println("\nScoreboard:");
            System.out.println(nameX + " (" + uiIcon("X") + "): " + winsX);
            System.out.println(nameO + " (" + uiIcon("O") + "): " + winsO);
            System.out.println("Draws: " + draws);

            System.out.println("Starting a new game...\n");
        }
    }

    // --- NAME HELPER ---
    private String askNameForMark(String mark) {
        while (true) {
            System.out.print("Hi " + mark + "! What's your name? (q = quit) ");
            String s = in.nextLine().trim(); // trim whitespace
            checkQuit(s);
            if (!s.isEmpty()) return s;                     // if not empty, return the name
            System.out.println("That was empty — please try again.");
        }
    }

    // --- One game ---
    private String playOneGame() {
        String[] gameGrid = {"1", "2", "3", "4", "5", "6", "7", "8", "9"}; // initial "empty" grid
        Board board = new Board(); // create a Board instance to use its methods

        String current = "X"; // X starts
        while (true) {
            board.print(gameGrid); // render current grid
            String currentName = "X".equals(current) ? nameX : nameO; // get current player's name
            String ui = uiIcon(current); // get current player's icon
            System.out.println(currentName + " (" + ui + "), your move — pick a cell (1–9, q=quit):");

            int cell = readFreeCell1to9(gameGrid, currentName); // read a valid, free cell by using helpers below
            placeMark(gameGrid, cell, current); // place the mark on the grid by using helper below

            if (board.isWin(gameGrid, current)) {
                board.print(gameGrid);
                System.out.println("🎉 " + currentName + " (" + ui + ") wins!");
                return current; // return "X" or "O"
            }

            if (board.isDraw(gameGrid)) {
                board.print(gameGrid);
                System.out.println("🤝 It's a draw!");
                return "D";
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
            checkQuit(s);
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