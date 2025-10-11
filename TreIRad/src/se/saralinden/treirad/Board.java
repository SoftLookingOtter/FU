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
}

