package se.saralinden.treirad;

/**
 * Entry point. Creates and starts the Game.
 * Responsibility: just call new Game().start().
 */

public class Main {
    public static void main(String[] args) {
        new Game().start();
        // new Game() creates a new Game object
        // .start() calls the start method on that object
    }
}
