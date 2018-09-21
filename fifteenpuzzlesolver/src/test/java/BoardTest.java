

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
    public void setBoardsetsBoard() {
        int[][] newBoard = {{15, 0, 4, 8},
                            {3, 6, 7, 1},
                            {2, 12, 5, 13},
                            {9, 10, 11, 14}};
        b.setBoard(newBoard);
        
        assertEquals("[15][x ][4 ][8 ]\n"
                   + "[3 ][6 ][7 ][1 ]\n"
                   + "[2 ][12][5 ][13]\n"
                   + "[9 ][10][11][14]", b.toString());
    }
    
    @Test
    public void toStringWorks() {
        assertEquals("[1 ][2 ][3 ][4 ]\n"
                   + "[5 ][6 ][7 ][8 ]\n"
                   + "[9 ][10][11][12]\n"
                   + "[13][14][15][x ]", b.toString());
    }
    
    @Test
    public void movesTiles() {
        b.moveTiles("s");
        assertEquals("[1 ][2 ][3 ][4 ]\n"
                   + "[5 ][6 ][7 ][8 ]\n"
                   + "[9 ][10][11][x ]\n"
                   + "[13][14][15][12]", b.toString());
        b.moveTiles("d");
        assertEquals("[1 ][2 ][3 ][4 ]\n"
                   + "[5 ][6 ][7 ][8 ]\n"
                   + "[9 ][10][x ][11]\n"
                   + "[13][14][15][12]", b.toString());
        b.moveTiles("w");
        assertEquals("[1 ][2 ][3 ][4 ]\n"
                   + "[5 ][6 ][7 ][8 ]\n"
                   + "[9 ][10][15][11]\n"
                   + "[13][14][x ][12]", b.toString());
        b.moveTiles("a");
        assertEquals("[1 ][2 ][3 ][4 ]\n"
                   + "[5 ][6 ][7 ][8 ]\n"
                   + "[9 ][10][15][11]\n"
                   + "[13][14][12][x ]", b.toString());
    }
    
    @Test
    public void doesNotAllowInvalidMoves() {
        assertFalse(b.moveTiles("a"));
        assertFalse(b.moveTiles("w"));
        
        b.moveTiles("s");
        b.moveTiles("s");
        b.moveTiles("s");
        
        assertFalse(b.moveTiles("s"));
        
        b.moveTiles("d");
        b.moveTiles("d");
        b.moveTiles("d");
        
        assertFalse(b.moveTiles("d"));
        
        assertFalse(b.moveTiles("dsfgsdfg"));
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
    
    @Test
    public void manhattanDistanceOfSolvedBoardIsZero() {
        assertEquals(0, b.manhattanDistance());
    }
    
    @Test
    public void manhattanDistanceOfOneBoardIsRight() {
        int[][] newBoard = {{15, 0, 4, 8},
                            {3, 6, 7, 1},
                            {2, 12, 5, 13},
                            {9, 10, 11, 14}};
        b.setBoard(newBoard);
        
        assertEquals(31, b.manhattanDistance());
    }
    
    @Test
    public void distanceFromSolvedTest() {
        b.moveTiles("w");
        b.moveTiles("d");
        b.moveTiles("s");
        b.moveTiles("d");
        
        assertEquals(6, b.distanceFromSolved());
    }
    
    @Test
    public void compareToTestSmaller() {
        Board b2 = new Board(N);
        int[][] newBoard = {{15, 0, 4, 8},
                            {3, 6, 7, 1},
                            {2, 12, 5, 13},
                            {9, 10, 11, 14}};
        b2.setBoard(newBoard);
        
        assertEquals(-31, b.compareTo(b2));
    }
    
    @Test
    public void compareToTestGreater() {
        Board b2 = new Board(N);
        int[][] newBoard = {{15, 0, 4, 8},
                            {3, 6, 7, 1},
                            {2, 12, 5, 13},
                            {9, 10, 11, 14}};
        b2.setBoard(newBoard);
        
        assertEquals(31, b2.compareTo(b));
    }
    
    @Test
    public void compareToTestEqual() {
        Board b2 = b.copy();
        
        assertEquals(0, b.compareTo(b2));
    }
}
