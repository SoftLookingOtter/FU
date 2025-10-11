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

        // --- Game state ---

        // --- 3x3 board with cells numbered 1 to 9 ---
        // Passed to Board for printing.
        String[] gameGrid = { "1","2","3","4","5","6","7","8","9" };

        Board board = new Board(); // create a Board object

        String current = "❌"; // X starts
        while (true) {
            board.print(gameGrid); // print the current board state
            System.out.println("Player " + current + ", pick a cell (1–9):");

            int cell = readFreeCell1to9(gameGrid); // read a free cell from user input by calling our helper method below
            placeMark(gameGrid, cell, current); // place the current player's mark at the chosen cell by calling our helper method below

            // check win
            if (board.isWin(gameGrid, current)) { // call the isWin method on the board object to check if current player has won (isWin comes from Board.java class so we call it on the board object we created above)
                board.print(gameGrid);
                System.out.println("🎉 Player " + current + " wins!");
                return; // end the game
            }

            // check draw
            if (board.isDraw(gameGrid)) {
                board.print(gameGrid);
                System.out.println("🤝 It's a draw!");
                return; // end the game
            }

            // switch player
            current = current.equals("❌") ? "⭕" : "❌"; // if current is "X", set to "O", else set to "X"
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
            } catch (NumberFormatException ignored) {} // if conversion failed, ignore and continue to the next line (i.e., print the error message below) and then loop again from the top to ask for input again

            System.out.println("Please type a number between 1 and 9."); // if we get here, input was invalid
        }
    }

    // 2: keep asking until the chosen cell is still showing a number (= free)
    private int readFreeCell1to9(String[] grid) {

        while (true) {
            int n = readCell1to9(); // call the method above (1.) to get a number 1–9
            if (grid[n - 1].equals(String.valueOf(n))) return n; // String.valueOf(n) converts int n to String, so we can compare it to grid[n - 1] (which is a String) // if the cell still shows its number, it's free -> return it

            System.out.println("That cell is taken. Pick a free one."); // if we get here, cell was taken
        }
    }

    // --- Game state update ---
    // 3: place a mark at a 1–9 cell
    private void placeMark(String[] grid, int cell1to9, String mark) {
        grid[cell1to9 - 1] = mark; // grid array points to the same memory as gameGrid array in start() -> so this changes gameGrid too
    }

}

