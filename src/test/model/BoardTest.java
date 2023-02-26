package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board testBoard;

    @BeforeEach
    void runBefore() {
        testBoard = new Board();
    }

    @Test
    void testConstructor() {
        // Test that 8x8 empty board is created
        assertEquals(8, testBoard.getBoard().length);
        assertEquals(8,testBoard.getBoard()[0].length);
        for (int i = 0;i < 8;i++) {
            for (int j = 0; j < 8;j++) {
                // Check that each square is empty
                assertEquals(" ", testBoard.getBoard()[i][j].toString());
            }
        }
    }

    @Test
    void testConstructorWithBoard() {
        // Should produce a copy of board passed into constructor
        assertTrue(testBoard.equals(new Board(testBoard)));
    }

    @Test
    void testSetPiece() {
        testBoard.setPiece('P', 'a', 3);
        assertEquals("P", testBoard.getBoard()[5][0].toString());
        testBoard.setPiece('k', 'h', 8);
        assertEquals("k", testBoard.getBoard()[0][7].toString());
        testBoard.setPiece(' ', 'a', 3);
        assertEquals(" ", testBoard.getBoard()[5][0].toString());
        testBoard.setPiece('p','b',1);
        assertEquals("p", testBoard.getBoard()[7][1].toString());
        testBoard.setPiece('p','d',1);
        assertEquals("p", testBoard.getBoard()[7][3].toString());
        testBoard.setPiece('p','e',1);
        assertEquals("p", testBoard.getBoard()[7][4].toString());
        testBoard.setPiece('p','f',1);
        assertEquals("p", testBoard.getBoard()[7][5].toString());
        testBoard.setPiece('p','g',1);
        assertEquals("p", testBoard.getBoard()[7][6].toString());

        testBoard.setPiece('r','i',1); //Temporary error handling; sets x to a
        assertEquals("r", testBoard.getBoard()[7][0].toString());
    }

    @Test
    void testMovePiece() { // Also tests equals
        // Test taking a piece
        Board compareBoard = new Board();
        testBoard.setPiece('Q', 'a', 3);
        testBoard.setPiece('p', 'c', 3);
        compareBoard.setPiece('Q','c',3);
        // Also tests equals()
        assertTrue(testBoard.movePiece('a',3,'c',3).equals(compareBoard));
        assertEquals("Q", testBoard.getBoard()[5][2].toString());
        assertEquals(" ", testBoard.getBoard()[5][0].toString());

        //Test moving a piece
        testBoard = new Board();
        compareBoard = new Board();
        testBoard.setPiece('R', 'c', 5);
        assertFalse(testBoard.equals(compareBoard));
        compareBoard.setPiece('R', 'c', 3);
        assertTrue(testBoard.movePiece('c',5,'c',3).equals(compareBoard));
        assertEquals("R", testBoard.getBoard()[5][2].toString());
        assertEquals(" ", testBoard.getBoard()[3][2].toString());
    }

    @Test
    void testToString() {
        String b1 = "8[ ,  ,  ,  ,  ,  ,  ,  ]\n" +
                    "7[ ,  ,  ,  ,  ,  ,  ,  ]\n" +
                    "6[ ,  ,  ,  ,  ,  ,  ,  ]\n" +
                    "5[ ,  ,  ,  ,  ,  ,  ,  ]\n" +
                    "4[ ,  ,  ,  ,  ,  ,  ,  ]\n" +
                    "3[ ,  ,  ,  ,  ,  ,  ,  ]\n" +
                    "2[ ,  ,  ,  ,  ,  ,  ,  ]\n" +
                    "1[ ,  ,  ,  ,  ,  ,  ,  ]\n" +
                    " [a, b, c, d, e, f, g, h]";
        assertEquals(b1,testBoard.toString());
        testBoard.setPiece('k','a',1);
        assertTrue(testBoard.toString().contains("k"));
    }

    @Test
    void testIsEmpty() {
        assertTrue(testBoard.isEmpty());
        testBoard.setPiece('k','a',1);
        assertFalse(testBoard.isEmpty());
    }
}