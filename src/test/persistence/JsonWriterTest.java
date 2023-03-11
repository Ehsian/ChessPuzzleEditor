package persistence;

import model.Board;
import org.junit.jupiter.api.Test;
import ui.Puzzle;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPuzzle() {
        try {
            Puzzle empty = new Puzzle(new ArrayList<>(),true);
            JsonWriter writer = new JsonWriter("testWriterEmptyPuzzle.json");
            writer.open();
            writer.write(empty);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPuzzle.json");
            ArrayList<Board> loadedPuzzle = reader.readPuzzle();
            assertTrue(loadedPuzzle.isEmpty());
        } catch (IOException e) {
            fail("No exception should have been thrown.");
        }
    }

    @Test
    void testWriterGeneralPuzzle() {
        try {
            Board board1 = new Board();
            board1.setPiece('K', 7, 4);
            Board board2 = new Board();
            board2.setPiece('K', 6, 4);
            ArrayList<Board>boards = new ArrayList<>();
            boards.add(board1);
            boards.add(board2);
            Puzzle puzzle = new Puzzle(boards, true);
            JsonWriter writer = new JsonWriter("testWriterGeneralPuzzle.json");
            writer.open();
            writer.write(puzzle);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPuzzle.json");
            ArrayList<Board> loadedPuzzle = reader.readPuzzle();
            assertEquals(loadedPuzzle.size(),2);
            assertTrue(loadedPuzzle.get(0).equals(board1));
            assertTrue(loadedPuzzle.get(1).equals(board2));
        } catch (IOException e) {
            fail("No exception should have been thrown.");
        }
    }
}
