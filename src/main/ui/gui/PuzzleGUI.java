package ui.gui;

import model.Board;
import model.Piece;
import org.json.JSONArray;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// Puzzle represents the user creation of "puzzles", which are ArrayLists of Boards. This class also includes
// the user interface, which incorporates playing, creating, and viewing puzzles.
public class PuzzleGUI extends JFrame {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;

    ArrayList<Board> puzzle;
    private int step = -1;

    private JButton[][] squares = new JButton[8][8];

    private static final String[] columns = {"a","b","c","d","e","f","g","h"};



    // Default constructor for building new puzzle
    public PuzzleGUI() {
        super("First-Board Setup:");
        initializeGraphics();
        setupBoard();
        runPuzzle();
        setVisible(false);
        dispose();
    }

    // Loading in a puzzle
    public PuzzleGUI(ArrayList<Board> puzzle) {
        super("Loaded Puzzle:");
        initializeGraphics();
        this.puzzle = puzzle;
        runPuzzle();
        setVisible(false);
        dispose();
    }

    // Constructor for loading test
    public PuzzleGUI(ArrayList<Board> puzzle, boolean test) {
        this.puzzle = puzzle;
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this menu will operate, and populates the buttons to be used
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        this.add(toolbar, BorderLayout.NORTH);
        JPanel boardPanel = new JPanel(new GridLayout(0, 9));
        boardPanel.setBorder(new LineBorder(Color.BLACK));
        makeBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    boardPanel.add(new JLabel("" + Math.abs(i - 8), SwingConstants.CENTER));
                }
                boardPanel.add(squares[j][i]);
            }
        }
        boardPanel.add(new JLabel(""));
        for (int i = 0; i < 8; i++) {
            boardPanel.add(new JLabel(columns[i], SwingConstants.CENTER));
        }
        this.add(boardPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Creates the squares of the chess board
    private void makeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton b = new JButton();
                // Sets empty chess squares to transparent image (64x64)px
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(Color.LIGHT_GRAY);
                } else {
                    b.setBackground(Color.DARK_GRAY);
                }
                squares[j][i] = b;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Updates the visual chess board to the specified board.
    private void updateBoardGraphics(Board board) {
        Piece[][]pieces = board.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j].isEmpty()) {
                    ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                    squares[j][i].setIcon(icon);
                } else {
                    String piece = pieces[i][j].toString();
                    String path = "./images/white" + piece + ".png";
                    if (Character.isLowerCase(piece.charAt(0))) {
                        path = "./images/black" + piece + ".png";
                    }
                    try {
                        BufferedImage image = ImageIO.read(new File(path));
                        Image scaledImage = image.getScaledInstance(64, 64, Image.SCALE_DEFAULT);
                        ImageIcon icon = new ImageIcon(scaledImage);
                        squares[j][i].setIcon(icon);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Performs first-time board setup
    public void setupBoard() {
        this.setTitle("First-Board Setup:");
        puzzle = new ArrayList<>();
        Board firstBoard = new Board();
        String command = "-1";
        while (!command.equals("0") || firstBoard.isEmpty()) { // Must have board that has movable piece to proceed
            command = displaySetupMenu();
            if (command.length() == 3) {
                char piece = command.charAt(0);
                char x = command.charAt(1);
                int y = Integer.parseInt(command.substring(2,3));
                firstBoard.setPiece(piece, x, y);
                updateBoardGraphics(firstBoard);
            }
        }
        puzzle.add(firstBoard);
    }

    // MODIFIES: this
    // EFFECTS: handles user input while selecting puzzle operation
    public void runPuzzle() { // Uses MenuA
        this.setTitle("Puzzle Menu:");
        MainGUI.puzzle = this;
        String command = "-1";
        while (!command.equals("0")) {
            command = JOptionPane.showInputDialog("1) Edit Puzzle\n2) Play Puzzle\n3) Show Answer\n0) Exit");
            switch (command) {
                case "1":
                    runEditPuzzle();
                    break;
                case "2":
                    if (puzzle.size() < 2) {
                        String message = "Please add a move to the puzzle first.";
                        JOptionPane.showMessageDialog(null, message);
                        break;
                    }
                    runPlayPuzzle();
                    break;
                case "3":
                    runShowPuzzle();
                    break;
            }
        }
    }

    // REQUIRES: !puzzle.isEmpty()
    // MODIFIES: this
    // EFFECTS: handles user input while editing puzzle
    public void runEditPuzzle() { // Uses MenuB
        this.setTitle("Editing Puzzle:");
        String command = "-1";
        while (!command.equals("0")) {
            printBoard(puzzle.size() - 1);
            command = JOptionPane.showInputDialog("1) Add Move\n2) Undo Move\n3) Reset\n0) Exit");
            switch (command) {
                case "1":
                    addMove(puzzle);
                    break;
                case "2":
                    if (puzzle.size() == 1) {
                        String message = "You cannot undo the first board.\n";
                        message += "If you wish to remake the starting board please reset the puzzle.";
                        JOptionPane.showMessageDialog(null, message);
                        continue;
                    }
                    puzzle.remove(puzzle.size() - 1);
                    break;
                case "3":
                    setupBoard();
                    break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Takes user-inputted move and adds resulting board to puzzle or player's attempt.
    private void addMove(ArrayList<Board> boards) {
        String startCoord = JOptionPane.showInputDialog("Coordinate of the piece to move:");
        String endCoord = JOptionPane.showInputDialog("Coordinate to move piece to:");
        char x1 = startCoord.charAt(0);
        int y1 = Integer.parseInt(startCoord.substring(1,2));
        char x2 = endCoord.charAt(0);
        int y2 = Integer.parseInt(endCoord.substring(1,2));
        boards.add(new Board(boards.get(boards.size() - 1)).movePiece(x1,y1,x2,y2));
    }

    // REQUIRES: puzzle.size() > 1
    // MODIFIES: this
    // EFFECTS: handles user input while playing puzzle
    public void runPlayPuzzle() { // Uses MenuC
        this.setTitle("Playing Puzzle:");
        String command = "-1";
        step = 1;
        ArrayList<Board> play = new ArrayList<>();
        play.add(puzzle.get(0));
        while (!command.equals("0")) {
            printBoard(0);
            command = JOptionPane.showInputDialog("1) Move\n2) Show Solution\n0) Quit/Back");
            switch (command) {
                case "1": // Move piece
                    movePiece(play);
                    if (step >= puzzle.size()) {
                        command = "0";
                    }
                    break;
                case "2": // Show answer
                    command = "0";
                    runShowPuzzle();
                    break;
            }
        }
    }

    // REQUIRES: puzzle.size() > 1
    // MODIFIES: this
    // EFFECTS: Processes current attempt to solve puzzle
    private void movePiece(ArrayList<Board> play) {
        addMove(play);
        if (play.get(step).equals(puzzle.get(step))) {
            step++;
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect!");
            play.remove(play.size() - 1);
        }
        if (step >= puzzle.size()) {
            printBoard(step - 1);
            JOptionPane.showMessageDialog(null, "You have correctly solved this puzzle!");
        }
    }

    // REQUIRES: puzzle.size() > 1
    // MODIFIES: this
    // EFFECTS: Process input to display next and previous move of puzzle.
    public void runShowPuzzle() { // Uses MenuD
        this.setTitle("Showing Solution:");
        String command = "-1";
        step = 0;
        while (!command.equals("0")) {
            printBoard(step);
            command = JOptionPane.showInputDialog("1) Next Move\n2) Previous Move\n0) Quit/Back");
            switch (command) {
                case "1":
                    if (step + 1 < puzzle.size()) {
                        step++;
                    }
                    break;
                case "2":
                    if (step - 1 >= 0) {
                        step--;
                    }
                    break;
            }
        }
    }

    // EFFECTS: Returns this Puzzle as JSONArray of boards
    public JSONArray toJson() {
        JSONArray puzzle = new JSONArray();
        for (Board board : this.puzzle) {
            puzzle.put(board.toJson());
        }
        return puzzle;
    }

    // MODIFIES: this
    // EFFECTS: Sets board display to the board at step (step is index)
    private void printBoard(int step) {
        this.setTitle("Current board (" + (step + 1) + "):");
        updateBoardGraphics(puzzle.get(step));
    }

    //EFFECTS: displays menu of options to user in setupBoard()
    private String displaySetupMenu() {
        String menu = "Enter a letter representing a piece followed by the coordinate.\n";
        menu += "For white pieces, capitalize the letter. For black pieces, keep the letter lowercase.\n";
        menu += "To set a square to be empty, enter a space followed by the coordinate.\n";
        menu += "e.g. Ke1 (white king on e1)\n";
        menu += "e.g. ke8 (black king on e8)\n";
        menu += "e.g.  e8 (sets e8 to empty)\n";
        menu += "0) Done\n";
        return JOptionPane.showInputDialog(menu);
    }
}
