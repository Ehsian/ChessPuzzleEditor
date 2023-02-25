package ui;
import model.Board;
import java.util.ArrayList;
import java.util.Scanner;

// Puzzle represents the user creation of "puzzles", which are ArrayLists of Boards. This class also includes
// the user interface, which incorporates playing, creating, and viewing puzzles.
public class Puzzle {

    ArrayList<Board> puzzle;
    private final Scanner input = new Scanner(System.in);

    public Puzzle(){
        System.out.println("Entering first-board setup...\n");
        setupBoard();
        runPuzzle();
    }

    // MODIFIES: this
    // EFFECTS: Performs first-time board setup
    public void setupBoard() {
        puzzle = new ArrayList<>();
        Board firstBoard = new Board();
        String command = "-1";
        while (!command.equals("0") || firstBoard.isEmpty()) { // Must have board that has movable piece to proceed
            displaySetupMenu();
            command = input.nextLine();
            if (command.length() == 3) {
                char piece = command.charAt(0);
                char x = command.charAt(1);
                int y = Integer.parseInt(command.substring(2,3));
                firstBoard.setPiece(piece, x, y);
                System.out.println(firstBoard);
            }
        }
        puzzle.add(firstBoard);
    }

    // MODIFIES: this
    // EFFECTS: handles user input while selecting puzzle operation
    public void runPuzzle() { // Uses MenuA
        int command = -1;
        while (command != 0) {
            displayMenuA();
            command = input.nextInt();
            switch (command){
                case 1: runEditPuzzle();
                case 2: runPlayPuzzle();
                case 3: runShowPuzzle();
            }
        }
    }

    // REQUIRES: !puzzle.isEmpty()
    // MODIFIES: this
    // EFFECTS: handles user input while editing puzzle
    public void runEditPuzzle() { // Uses MenuB
        int command = -1;
        while (command != 0) {
            printBoard(puzzle.size()-1);
            displayMenuB();
            command = input.nextInt();
            switch (command){
                case 1: addMove(puzzle);
                case 2: {
                    if (puzzle.size() == 1) {
                        System.out.println("You cannot undo the first board.");
                        System.out.println("If you wish to remake the starting board please reset the puzzle.");
                        continue;
                    }
                    puzzle.remove(puzzle.size()-1);
                }
                case 3: setupBoard();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Takes user-inputted move and adds resulting board to puzzle or player's attempt.
    private void addMove(ArrayList<Board> boards) {
        input.nextLine();
        System.out.print("Coordinate of the piece to move: ");
        String startCoord = input.nextLine();
        System.out.print("Coordinate to move piece to: ");
        String endCoord = input.nextLine();
        char x1 = startCoord.charAt(0);
        int y1 = Integer.parseInt(startCoord.substring(1,2));
        char x2 = endCoord.charAt(0);
        int y2 = Integer.parseInt(endCoord.substring(1,2));
        boards.add(new Board(boards.get(boards.size()-1)).movePiece(x1,y1,x2,y2));
    }

    // REQUIRES: puzzle.size() > 1
    // EFFECTS: handles user input while playing puzzle
    @SuppressWarnings("methodlength")
    public void runPlayPuzzle() { // Uses MenuC
        int command = -1;
        int step = 1;
        ArrayList<Board> play = new ArrayList<>();
        play.add(puzzle.get(0));
        while (command != 0) {
            printBoard(0);
            displayMenuC();
            command = input.nextInt();
            switch (command){
                case 1: { // Move piece
                    addMove(play);
                    if (play.get(step).equals(puzzle.get(step))) {
                        step++;
                    } else {
                        System.out.println("Incorrect! ");
                        play.remove(play.size()-1);
                    } if (step >= puzzle.size()) {
                        command = 0;
                        printBoard(step-1);
                        System.out.println("You have correctly solved this puzzle! Press enter to continue.");
                        input.nextLine();
                    }
                }
                case 2: { // Show answer
                    command = 0;
                    runShowPuzzle();
                }
            }
        }
    }

    // REQUIRES: puzzle.size() > 1
    // EFFECTS: Process input to display next and previous move of puzzle.
    public void runShowPuzzle() { // Uses MenuD
        int command = -1;
        int step = 0;
        while (command != 0) {
            printBoard(step);
            displayMenuD();
            command = input.nextInt();
            switch (command) {
                case 1: {
                    if (step + 1 < puzzle.size()) {
                        step++;
                    } else {
                        System.out.println("This is already the final position.");
                    }
                }
                case 2: {
                    if (step - 1 >= 0) {
                        step--;
                    } else {
                        System.out.println("This is already the first position.");
                    }
                }
            }
        }
    }

    // EFFECTS: Prints board at step (step is index)
    private void printBoard(int step) {
        System.out.println("\nCurrent board (" + (step + 1) + "):");
        System.out.println(puzzle.get(step));
    }

    // EFFECTS: displays menu of options to user in runPuzzle()
    private void displayMenuA() {
        System.out.println("\nInput Command:");
        System.out.println("\t0) Quit/Back");
        System.out.println("\t1) Edit Puzzle");
        System.out.println("\t2) Play Puzzle");
        System.out.println("\t3) Show Solution");
    }

    // EFFECTS: displays menu of options to user in runEditPuzzle()
    private void displayMenuB() {
        System.out.println("\nInput Command (Editing Puzzle):");
        System.out.println("\t0) Quit/Back (changes are saved)");
        System.out.println("\t1) Move Piece");
        System.out.println("\t2) Undo Move");
        System.out.println("\t3) Reset Puzzle");
    }

    // EFFECTS: displays menu of options to user in runPlayPuzzle()
    private void displayMenuC() {
        System.out.println("\nInput Command (Playing Puzzle):");
        System.out.println("\t0) Quit/Back");
        System.out.println("\t1) Move Piece");
        System.out.println("\t2) Give Up (Show Solution)");
    }

    // EFFECTS: displays menu of options to user in runShowPuzzle()
    private void displayMenuD() {
        System.out.println("\nInput Command (Showing Solution):");
        System.out.println("\t0) Quit/Back");
        System.out.println("\t1) Next Move");
        System.out.println("\t2) Previous Move");
    }

    //EFFECTS: displays menu of options to user in setupBoard()
    private void displaySetupMenu(){
        System.out.println("\nEnter a letter representing a piece followed by the coordinate.");
        System.out.println("For white pieces, capitalize the letter. For black pieces, keep the letter lowercase.");
        System.out.println("To set a square to be empty, enter a space followed by the coordinate");
        System.out.println("e.g. Ke1 (white king on e1)");
        System.out.println("e.g. ke8 (black king on e8)");
        System.out.println("e.g.  e8 (sets e8 to empty)");
        System.out.println("\t0) Done");
    }
}
