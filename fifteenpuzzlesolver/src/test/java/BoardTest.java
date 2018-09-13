

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import puzzle.fifteenpuzzlesolver.Board;

/**
 *
 * @author Matias Wargelin
 */
public class BoardTest {
    final int N = 4;
    Board b;   
    
    public BoardTest() {   
    }
    
    @Before
    public void setUp() {
        b = new Board(N);
    }

    @Test
    public void getNReturnsLength() {
        assertEquals(N, b.getN());
    }
    
    @Test
    public void toStringWorks() {
        assertEquals(b.toString(),
                          "[1 ][2 ][3 ][4 ]\n"
                        + "[5 ][6 ][7 ][8 ]\n"
                        + "[9 ][10][11][12]\n"
                        + "[13][14][15][x ]\n");
    }
    
    @Test
    public void movesTiles() {
        b.moveTiles("s");
        assertEquals(b.toString(),
                          "[1 ][2 ][3 ][4 ]\n"
                        + "[5 ][6 ][7 ][8 ]\n"
                        + "[9 ][10][11][x ]\n"
                        + "[13][14][15][12]\n");
        b.moveTiles("d");
        assertEquals(b.toString(),
                          "[1 ][2 ][3 ][4 ]\n"
                        + "[5 ][6 ][7 ][8 ]\n"
                        + "[9 ][10][x ][11]\n"
                        + "[13][14][15][12]\n");
        b.moveTiles("w");
        assertEquals(b.toString(),
                          "[1 ][2 ][3 ][4 ]\n"
                        + "[5 ][6 ][7 ][8 ]\n"
                        + "[9 ][10][15][11]\n"
                        + "[13][14][x ][12]\n");
        b.moveTiles("a");
        assertEquals(b.toString(),
                          "[1 ][2 ][3 ][4 ]\n"
                        + "[5 ][6 ][7 ][8 ]\n"
                        + "[9 ][10][15][11]\n"
                        + "[13][14][12][x ]\n");
    }
    
    @Test
    public void isSolvedReturnsTrueIfBoardIsSolved() {
        assertTrue(b.isSolved());
    }
    
    @Test
    public void isSolvedReturnsFalseIfBoardIsNotSolved() {
        b.moveTiles("s");
        assertFalse(b.isSolved());
    }
    
    @Test
    public void ShuffleShufflesTheBoard() {
        b.Shuffle();
        assertFalse(b.isSolved());
    }
}
