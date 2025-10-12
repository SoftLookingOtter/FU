package se.saralinden.treirad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Game loop & UI controller.
 * Responsibilities:
 * - Owns the game state (3x3 grid) and manages turn order.
 * - Chooses mode (PvP or vs bot), asks player names, and keeps a running scoreboard.
 * - Reads and validates input, supports 'q' to quit, and applies moves.
 * - Delegates rendering and win/draw checks to Board.
 * - In bot mode, selects a random free move for O.
 * <p>
 * Helpers in this class:
 * - askYesNo(prompt)              – ask a y/n question.
 * - askNameForMark(mark)          – read a non-empty player name.
 * - readCell1to9(who)             – read a number 1–9.
 * - readFreeCell1to9(grid, who)   – keep asking until the cell is free.
 * - placeMark(grid, cell, mark)   – update board state.
 * - randomFreeCell(grid)          – pick a random free cell (bot).
 * - uiIcon(mark)                  – display symbol/color for X/O.
 * - checkQuit(s)                  – exit on q/quit/exit.
 */

public class Game {

    // ANSI colors
    private static final String RED = "\u001B[31m";
    private static final String BRIGHT_BLUE = "\u001B[94m";
    private static final String BOLD = "\u001B[1m";
    private static final String RESET = "\u001B[0m";

    private final Scanner in = new Scanner(System.in); // final to prevent reassignment to another Scanner
    private final Random rng = new Random();

    private String nameX, nameO; // player names for X and O
    private int winsX = 0, winsO = 0, draws = 0;
    private boolean vsBot = false; // play vs bot?

    public void start() {
        // --- Welcome & instructions ---
        System.out.println();
        System.out.println("🦝 Raccoon Referee: Welcome to Tic-Tac-Toe!");
        System.out.println("Marks: " + uiIcon("X") + " vs " + uiIcon("O") + ". Choose cells 1–9. Type 'q' to quit.");
        System.out.println();

        // 2) Choose mode
        vsBot = askYesNo("Should " + uiIcon("O") + " be a bot? (y/n): ");
        System.out.println();
        System.out.println(
                vsBot
                        ? "Mode: Player vs Bot (" + uiIcon("O") + ")"
                        : "Mode: Player vs Player (" + uiIcon("X") + " vs " + uiIcon("O") + ")"
        );

        // 3) Ask names depending on mode (needed for scoreboard and prompts)
        nameX = askNameForMark("X");

        if (vsBot) {
            nameO = "Raccoon Bot"; // keep logic X/O; display name for O
        } else {
            nameO = askNameForMark("O");
        }

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

    // --- One game ---
    private String playOneGame() {
        String[] gameGrid = {"1", "2", "3", "4", "5", "6", "7", "8", "9"}; // initial "empty" grid
        Board board = new Board(); // create a Board instance to use its methods
        String current = "X"; // X starts

        while (true) {
            board.print(gameGrid);
            String currentName = "X".equals(current) ? nameX : nameO;
            String ui = uiIcon(current);

            int cell;
            if (vsBot && "O".equals(current)) {
                // Bot spelar som O: välj slumpmässig ledig ruta
                cell = randomFreeCell(gameGrid);
                System.out.println(currentName + " (" + ui + ") chooses: " + cell);
                System.out.println();

            } else {
                System.out.println(currentName + " (" + ui + "), your move — pick a cell (1–9, q=quit):");
                cell = readFreeCell1to9(gameGrid, currentName);
            }

            placeMark(gameGrid, cell, current);

            if (board.isWin(gameGrid, current)) {
                board.print(gameGrid);
                System.out.println("🎉 " + currentName + " (" + ui + ") wins!");
                return current;
            }

            if (board.isDraw(gameGrid)) {
                board.print(gameGrid);
                System.out.println("🤝 It's a draw!");
                return "D";
            }
            current = "X".equals(current) ? "O" : "X";
        }

    }

    // --- HELPERS: INPUT & STATE UPDATES ---

    // --- UI HELPER ---
    // Map logic mark -> display icon (keep logic X/O)
    private String uiIcon(String mark) {
        return "X".equals(mark) ? (RED + "✖" + RESET) : (BOLD + BRIGHT_BLUE + "○" + RESET);
    }

    // --- QUIT HELPER ---
    // Type 'q'/'quit'/'exit' anywhere to quit
    private void checkQuit(String s) {
        if (s.equalsIgnoreCase("q") || s.equalsIgnoreCase("quit") || s.equalsIgnoreCase("exit")) {
            System.out.println("Goodbye!");
            System.exit(0); // terminate the program with inbuilt method System.exit
        }
    }

    // --- NAME HELPER ---
    private String askNameForMark(String mark) {
        String icon = uiIcon(mark);
        while (true) {
            System.out.println();
            System.out.print("Hi " + icon + "! What's your name? ");
            String s = in.nextLine().trim();
            checkQuit(s);
            if (!s.isEmpty()) return s;
            System.out.println("That was empty — please try again.");
        }
    }

    // --- YES/NO HELPER ---
    private boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim().toLowerCase();
            checkQuit(s);
            if (s.equals("y") || s.equals("yes")) return true;
            if (s.equals("n") || s.equals("no")) return false;
            System.out.println("Please answer y/yes or n/no.");
            System.out.println();
        }
    }

    // --- INPUT HELPERS ---

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
                return n; // if the cell still shows a number, it's free -> return it
            System.out.println("That cell is taken, " + who + ". Pick a free one."); // otherwise, show message and reprompt
        }
    }

    // --- BOT HELPER ---
    // Random free cell number 1..9
    private int randomFreeCell(String[] grid) {
        List<Integer> free = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (grid[i].equals(String.valueOf(i + 1))) {
                free.add(i + 1);
            }
        }
        return free.get(rng.nextInt(free.size()));
    }

    // --- STATE UPDATE ---
    // Place a mark ("X" or "O") at a 1–9 cell
    private void placeMark(String[] grid, int cell1to9, String mark) {
        grid[cell1to9 - 1] = mark; // mutates the provided array to place the mark
    }
}