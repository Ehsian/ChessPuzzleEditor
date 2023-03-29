package ui.gui;

import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

// Starts up puzzle, determines whether to use existing or make new puzzle.
public class MainGUI extends JFrame {
    static PuzzleGUI puzzle;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    // EFFECTS: Default constructor, builds the main menu
    public MainGUI() {
        super("Main Menu");
        initializeGraphics();
    }

    // EFFECTS: Creates an instance of the main menu
    public static void main(String[] args) {
        new MainGUI();
    }

    // EFFECTS: Saves the current puzzle
    private static void savePuzzle(PuzzleGUI puzzle) {
        if (Objects.isNull(puzzle)) {
            String message = "There is nothing to save!\nPlease create or load a puzzle before saving.";
            JOptionPane.showMessageDialog(null,message);
            return;
        }
        String filename = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.json'").format(new Date());
        try {
            JsonWriter writer = new JsonWriter(filename);
            writer.open();
            writer.write(puzzle);
            writer.close();
            String message = "Saved current puzzle to './data/" + filename + "' successfully.";
            JOptionPane.showMessageDialog(null,message);
        } catch (FileNotFoundException e) {
            String message = "Unable to write to file: ./data/" + filename;
            JOptionPane.showMessageDialog(null,message);
        }
    }

    // EFFECTS: Loads a previously saved puzzle
    private void loadPuzzle(String filename) {
        try {
            JsonReader reader = new JsonReader("./data/" + filename);
            setVisible(false);
            new PuzzleGUI(reader.readPuzzle());
            setVisible(true);
        } catch (IOException e) {
            String message = "Unable to read from file: " + "./data/" + filename;
            JOptionPane.showMessageDialog(null, message);
        }
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this menu will operate, and populates the buttons to be used
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds start menu buttons to a panel.
    private void createButtons() {
        JPanel panel = new JPanel(new GridLayout(1,4));

        panel.add(exitButton());
        panel.add(newButton());
        panel.add(saveButton());
        panel.add(loadButton());

        this.add(panel);
    }

    // EFFECTS: Returns a button that exits the program on click.
    private JButton exitButton() {
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thanks for playing!");
                setVisible(false);
                dispose();
            }
        });
        return exit;
    }

    // EFFECTS: Returns a button that creates a new puzzle on click.
    private JButton newButton() {
        JButton newPuzzle = new JButton("New Puzzle");
        newPuzzle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new PuzzleGUI();
                setVisible(true);
            }
        });
        return newPuzzle;
    }

    // EFFECTS: Returns a button that saves the current puzzle on click.
    private JButton saveButton() {
        JButton savePuzzle = new JButton("Save Current Puzzle");
        savePuzzle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePuzzle(puzzle);
            }
        });
        return savePuzzle;
    }

    // EFFECTS: Returns a button that prompts the user for a file location to load a puzzle from.
    private JButton loadButton() {
        JButton loadPuzzle = new JButton("Load Puzzle");
        loadPuzzle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String location = JOptionPane.showInputDialog("File Name to Load:");
                loadPuzzle(location);
            }
        });
        return loadPuzzle;
    }
}
