package ui;

import org.json.JSONWriter;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

// Starts up puzzle, determines whether to use existing or make new puzzle.
public class Main {
    static int input = -1;
    static Scanner scan = new Scanner(System.in);
    static Puzzle puzzle;

    // EFFECTS: Handles startup user input
    public static void main(String[] args) {
        input = -1;
        while (input != 0) {
            displayMenu();
            input = scan.nextInt();
            switch (input) {
                case 1:
                    new Puzzle();
                    break;
                case 2:
                    savePuzzle(puzzle);
                    break;
                case 3:
                    loadPuzzle();
                    break;
            }
        }
    }

    private static void savePuzzle(Puzzle puzzle) {
        if (Objects.isNull(puzzle)) {
            System.out.println("There is nothing to save!");
            System.out.println("Please create or load a puzzle before saving.");
            return;
        }
        String filename = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.json'").format(new Date());
        try {
            JsonWriter writer = new JsonWriter(filename);
            writer.open();
            writer.write(puzzle);
            writer.close();
            System.out.println("Saved current puzzle to './data/" + filename + "' successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: ./data/" + filename);
        }
    }

    private static void loadPuzzle() {
        scan.nextLine();
        System.out.print("File name to load: ");
        String filename = scan.nextLine();
        try {
            JsonReader reader = new JsonReader("./data/" + filename);
            new Puzzle(reader.readPuzzle());
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + "./data/" + filename);
        }
    }

    // EFFECTS: Displays the user's possible input options
    private static void displayMenu() {
        System.out.println("\nInput Command:");
        System.out.println("\t0) Exit");
        System.out.println("\t1) New Puzzle");
        System.out.println("\t2) Save Current Puzzle");
        System.out.println("\t3) Load Puzzle");
    }
}
