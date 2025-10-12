package se.saralinden.treirad; // package declaration

 /**
 * Stateless board utilities (no data here).
 * - print(String[] boardData): render the 3x3 grid.
 * - isWin(String[] b, String mark): check three-in-a-row for X/O.
 * - isDraw(String[] b): check if no free cells remain.
 */

public class Board {

    // ANSI colors
    private static final String RESET = "\u001B[0m";
    private static final String RED   = "\u001B[31m"; // X
    private static final String BLUE  = "\u001B[34m"; // O
    private static final String GRAY  = "\u001B[90m"; // numbers

    // Colorize without mutating data
    private String colorize(String s) {
        if ("X".equals(s)) return RED  + "X" + RESET;
        if ("O".equals(s)) return BLUE + "O" + RESET;
        return GRAY + s + RESET; // 1–9
    }

    public void print(String[] boardData) {
        System.out.println();
        System.out.println(" " + colorize(boardData[0]) + " ║ " + colorize(boardData[1]) + " ║ " + colorize(boardData[2]));
        System.out.println("═══╬═══╬═══");
        System.out.println(" " + colorize(boardData[3]) + " ║ " + colorize(boardData[4]) + " ║ " + colorize(boardData[5]));
        System.out.println("═══╬═══╬═══");
        System.out.println(" " + colorize(boardData[6]) + " ║ " + colorize(boardData[7]) + " ║ " + colorize(boardData[8]));
        System.out.println();
    }

    // --- GAME LOGIC HELPERS ---

    // 1. Return true if mark ("X" or "O") has a winning line on the board
    public boolean isWin(String[] b, String mark) {
        int[][] lines = { // an array of arrays, each with 3 cell indices that form a winning line
                {0,1,2}, {3,4,5}, {6,7,8}, // rows
                {0,3,6}, {1,4,7}, {2,5,8}, // columns
                {0,4,8}, {2,4,6}           // diagonals
        };

        for (int[] line : lines) { // for each line in the lines array
            if (b[line[0]].equals(mark) && b[line[1]].equals(mark) && b[line[2]].equals(mark)) { // check if all 3 cells in the line contain the same mark
                return true; // found a winning line -> return true
            }
        }
        return false; // no winning line found -> return false
    }


    // 2. Return true if the board is full (no free cells)
    public boolean isDraw(String[] b) { // b = current board state array
        for (int i = 0; i < 9; i++) { // check each cell
            if (b[i].equals(String.valueOf(i + 1))) return false; // if any cell still shows a number, it's free -> not a draw
        }
        return true; // all cells are filled -> it's a draw
    }
}

