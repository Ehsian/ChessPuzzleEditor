package ui;

import java.util.Scanner;

// Starts up puzzle, determines whether to use existing or make new puzzle.
public class Main {

    // EFFECTS: Handles startup user input
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int input = -1; // TODO: Exception for when not an integer

        while (input != 0) {
            displayMenu();
            input = scan.nextInt();
            switch (input) {
                case 1: new Puzzle();
                //case 2 -> System.out.println("WIP");
            }
        }
    }

    // EFFECTS: Displays the user's possible input options
    private static void displayMenu() {
        System.out.println("\nInput Command:");
        System.out.println("\t0) Exit");
        System.out.println("\t1) New Puzzle");
        System.out.println("\t2) Load Puzzle (WIP)");
    }
}
