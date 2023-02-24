package model;

// Simple object representation of a chess piece for ease of expansion in the future.
// Currently functions similarly to String or Character.
public class Piece {
    String str = " ";

    public Piece(){}

    public Piece(String str) {
        this.str = str;
    }

    // EFFECTS: Returns str, the character associated with this piece.
    public String toString() {
        return str;
    }

    // EFFECTS: Returns a boolean, whether or not the square this piece occupies is empty.
    public boolean isEmpty() {
        return str.equals(" ");
    }
}
