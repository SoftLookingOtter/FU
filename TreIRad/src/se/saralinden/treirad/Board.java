package se.saralinden.treirad;

public class Board {
    public void print(String[] boardData) {
        System.out.println();
        System.out.println(" " + boardData[0] + " | " + boardData[1] + " | " + boardData[2]);
        System.out.println("---+---+---");
        System.out.println(" " + boardData[3] + " | " + boardData[4] + " | " + boardData[5]);
        System.out.println("---+---+---");
        System.out.println(" " + boardData[6] + " | " + boardData[7] + " | " + boardData[8]);
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
            if (b[i].equals(String.valueOf(i + 1))) return false; // if any cell still shows its number, it's free -> not a draw
        }
        return true; // inga fria rutor = oavgjort
    }
}

