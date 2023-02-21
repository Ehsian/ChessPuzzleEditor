package model;

import java.util.Arrays;

public class Board {
    private Piece[][] board;

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
    // EFFECTS: Sets current board's piece map to input board's piece map.
    public Board(Board board) {
        this.board = board.getBoard();
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
    public void addPiece(char piece, char x, int y) {
        board[Math.abs(7 - (y - 1))][charToX(x)] = charToPiece(piece);
    }

    // REQUIRES: Piece exists at (x1,y1) & it is valid for piece at (x1,y1) to move to (x2,y2)
    // MODIFIES: this
    // EFFECTS: Moves piece at (x1,y1) to (x2,y2). Replaces and returns existing piece on (x2,y2).
    public Piece movePiece(char x1, int y1, char x2, int y2) {

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

    // REQUIRES: piece represents valid piece's character
    // EFFECTS: Returns Piece object representation of character
    private Piece charToPiece(char piece) {
        switch (piece) {
            case 'P':
                return new Pawn();
            case 'K':
                return new King();
            default:
                return new Piece(); //replace with error handling in future
        }
    }

    private int charToX(char x) {
        switch (x) {
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            default: return -1; //switch with error handling in future
        }
    }
}
