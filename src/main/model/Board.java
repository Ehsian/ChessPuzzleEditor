package model;

import org.json.JSONObject;

import java.util.Arrays;

// Representation of a chess board as a 8x8 2D array of Pieces with board-manipulating functions.
public class Board {
    private final Piece[][] board;

    // EFFECTS: Creates 8x8 array of empty Pieces.
    public Board() {
        this.board = new Piece[8][8];
        for (int i = 0;i < 8;i++) {
            for (int j = 0; j < 8;j++) {
                board[i][j] = new Piece();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets current board's piece map to input board's piece map. (Deep copy, not reference)
    public Board(Board board) {
        this.board = new Piece[8][8];
        for (int i = 0;i < 8;i++) {
            for (int j = 0; j < 8;j++) {
                this.board[i][j] = board.getBoard()[i][j];
            }
        }
    }

    // EFFECTS: Returns this board as its array of pieces. (piece map)
    public Piece[][] getBoard() {
        return this.board;
    }

    // REQUIRES: piece represents a valid piece's character,
    //           x is valid board x-coordinate (a-h),
    //           y is valid board y-coordinate (1-8)
    // MODIFIES: this
    // EFFECTS: Sets the piece at the input coordinates to input piece.
    public void setPiece(char piece, char x, int y) {
        board[getY(y)][getX(x)] = charToPiece(piece);
        EventLog.getInstance().logEvent(new Event("A Piece has been set on a board."));
    }

    // REQUIRES: piece represents a valid piece's character,
    //           i is valid row on 2D Piece array
    //           j is valid column on 2D Piece array
    // MODIFIES: this
    // EFFECTS: Sets the piece at the input coordinates to input piece.
    public void setPiece(char piece, int i, int j) {
        board[i][j] = charToPiece(piece);
        EventLog.getInstance().logEvent(new Event("A Piece has been set on a board."));
    }

    // REQUIRES: Piece exists at (x1,y1) and it is valid for piece at (x1,y1) to move to (x2,y2)
    // MODIFIES: this
    // EFFECTS: Moves piece at (x1,y1) to (x2,y2). Replaces existing piece on (x2,y2). Returns resulting board.
    public Board movePiece(char x1, int y1, char x2, int y2) {
        Piece from = board[getY(y1)][getX(x1)];
        // Piece to = board[getY(y2)][getX(x2)]; // Piece that gets "taken"

        board[getY(y1)][getX(x1)] = new Piece();
        board[getY(y2)][getX(x2)] = from;
        EventLog.getInstance().logEvent(new Event("A Piece has been moved on a board."));
        return this;
    }

    // EFFECTS: Returns a string that represents the board as text. (with coordinates)
    public String toString() {
        String[] temp = {"a","b","c","d","e","f","g","h"};
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 8;i++) {
            str.append(8 - i).append(Arrays.toString(board[i])).append("\n");
        }
        str.append(" ").append(Arrays.toString(temp));
        return str.toString();
    }

    // EFFECTS: Returns a boolean, whether this board is empty.
    public boolean isEmpty() {
        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (!piece.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    // EFFECTS: Returns whether this board is equal to b.
    public boolean equals(Board b) {
        for (int i = 0;i < 8;i++) {
            for (int j = 0;j < 8;j++) {
                if (!this.board[i][j].toString().equals(b.board[i][j].toString())) {
                    return false;
                }
            }
        }
        return true;
    }

    // REQUIRES: piece represents valid piece's character
    // EFFECTS: Returns Piece object representation of character
    private Piece charToPiece(char piece) {
        switch (piece) {
            case 'P': case 'p': case 'R': case 'r': case 'N': case 'n':
            case 'B': case 'b': case 'Q': case 'q': case 'K': case 'k':
                return new Piece(String.valueOf(piece));
            default:
                return new Piece(); //replace with error handling in future
        }
    }

    // REQUIRES: x is a valid chess board x-coordinate (a-h)
    // EFFECTS: Returns an indexed interpretation of the chess board x-coordinate
    private int getX(char x) {
        switch (x) {
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            default: return 0; //switch with error handling in future
        }
    }

    // REQUIRES: y is a valid chess board y-coordinate (1-8)
    // EFFECTS: Returns an indexed interpretation of the chess board y-coordinate
    private int getY(int y) {
        return Math.abs(7 - (y - 1));
    }

    // EFFECTS: Returns this board as a string of pieces with their coordinates.
    private String boardToString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!board[i][j].isEmpty()) {
                    str.append(board[i][j].toString());
                    str.append(i);
                    str.append(j);
                }
            }
        }
        return str.toString();
    }

    // EFFECTS: Returns this board as a JSONObject
    public JSONObject toJson() {
        JSONObject jsonBoard = new JSONObject();
        jsonBoard.put("Board",boardToString());
        return jsonBoard;
    }

    // REQUIRES: str is a valid and acceptable data string
    // MODIFIES: this
    // EFFECTS: Sets pieces on this board as saved in str. Returns this.
    public Board stringToBoard(String str) {
        // Note that piece coordinates come in bundles of 3 characters long
        for (int i = 0; i < str.length(); i += 3) {
            char piece = str.charAt(i);
            int row = Integer.parseInt(str.substring(i + 1, i + 2));
            int column = Integer.parseInt(str.substring(i + 2, i + 3));
            setPiece(piece,row,column);
        }
        return this;
    }
}
