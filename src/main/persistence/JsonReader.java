package persistence;

import model.Board;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the JSONArray of Boards from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Board> readPuzzle() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parsePuzzle(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ArrayList<Board> from JSON object and returns it
    private ArrayList<Board> parsePuzzle(JSONArray jsonArray) {
        ArrayList<Board> puzzle = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            puzzle.add(new Board().stringToBoard(jsonArray.getJSONObject(i).get("Board").toString()));
        }
        return puzzle;
    }
}