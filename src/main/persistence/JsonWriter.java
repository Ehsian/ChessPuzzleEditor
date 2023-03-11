package persistence;

import org.json.JSONArray;
import org.json.JSONWriter;
import ui.Puzzle;

import java.io.*;

public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    // EFFECTS: Constructs writer to write to destination file
    public JsonWriter(String fileName) {
        this.destination = "./data/" + fileName;
    }

    // MODIFIES: this
    // EFFECTS: Opens writer and throws FileNotFoundException if destination invalid
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: Write JSON representation of Puzzle to file
    public void write(Puzzle puzzle) throws NullPointerException {
        JSONArray data = puzzle.toJson();
        writer.print(data);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
