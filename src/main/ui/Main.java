package ui;

import model.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Board testboard = new Board();
        testboard.addPiece('P','a',3);
        System.out.println(testboard);
        Pawn testpawn = new Pawn();
        System.out.println(testpawn);
        Piece[] test = new Piece[2];
        test[0] = testpawn;
        test[1] = new King();
        System.out.println(Arrays.toString(test));
    }
}
