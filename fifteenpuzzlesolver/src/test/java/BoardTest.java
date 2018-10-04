

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
    public void getMovesSoFarTest() {
        b.moveTiles("d");
        b.moveTiles("d");
        b.moveTiles("d");
        b.moveTiles("d"); //invalid move
        
        assertEquals(3, b.getMovesSoFar());
    }
    
    @Test
    public void setAndGetPreviousBoardTest() {
        int[][] state = {{15, 0, 4, 8},
                            {3, 6, 7, 1},
                            {2, 12, 5, 13},
                            {9, 10, 11, 14}};
        Board newBoard = new Board(4);
        newBoard.setBoard(state);
        b.setPreviousBoard(newBoard);
        assertEquals(newBoard, b.getPreviousBoard());
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
    public void findTileTest() {
        int[] tile = b.findTile(3);
        assertEquals(0, tile[0]);
        assertEquals(2, tile[1]);
    }
    
    @Test
    public void distanceFromSolvedTest() {
        b.moveTiles("w");
        b.moveTiles("d");
        b.moveTiles("s");
        b.moveTiles("d");
        
        //3 moves (one move was invalid)
        //+ 3 tiles 1 move away from being solved = 6
        assertEquals(6, b.distanceFromSolved());
    }
    
    @Test
    public void distanceFromSolved2Test() {
        int[][] newBoard = {{1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10, 11, 12},
                            {13, 15, 14, 0}};
        b.setBoard(newBoard);

        b.moveTiles("s");
        
        //1 move
        //+ 3 manhattan distance
        //+ 2 linear conflicts = 6
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
    public void toStringWorks() {
        assertEquals("[1 ][2 ][3 ][4 ]\n"
                   + "[5 ][6 ][7 ][8 ]\n"
                   + "[9 ][10][11][12]\n"
                   + "[13][14][15][x ]", b.toString());
    }
    
    @Test
    public void compareToTestEqual() {
        Board b2 = b.copy();
        
        assertEquals(0, b.compareTo(b2));
    }
    
    @Test
    public void noLinearConlicts() {
        assertEquals(0, b.linearConflicts());
    }
    
    @Test
    public void linearConflictOnARow() {
        int[][] newBoard = {{1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10, 11, 12},
                            {13, 15, 14, 0}};
        b.setBoard(newBoard);
        
        assertEquals(2, b.linearConflicts());
    }
    
    @Test
    public void linearConflictOnAColumn() {
        int[][] newBoard = {{5, 2, 3, 4},
                            {1, 6, 7, 8},
                            {9, 10, 11, 12},
                            {13, 14, 15, 0}};
        b.setBoard(newBoard);
        
        assertEquals(2, b.linearConflicts());
    }
    
    @Test
    public void noLinearConflicts2() {
        int[][] newBoard = {{15, 0, 4, 8},
                            {3, 6, 7, 1},
                            {2, 12, 5, 13},
                            {9, 10, 11, 14}};
        b.setBoard(newBoard);
        
        assertEquals(0, b.linearConflicts());
    }
    
    @Test
    public void manyLinearConflicts() {
        int[][] newBoard = {{2, 1, 7, 12},
                            {5, 6, 3, 8},
                            {9, 10, 11, 4},
                            {15, 14, 13, 0}};
        b.setBoard(newBoard);
        
        assertEquals(16, b.linearConflicts());
    }
    
    @Test
    public void matchesStateTrueTest() {
        int[][] state = {{1, 2, 3, 4},
                         {5, 6, 7, 8},
                         {9, 10, 11, 12},
                         {13, 14, 15, 0}};
        
        assertTrue(b.matchesState(state));
    }
    
    @Test
    public void matchesStateFalseTest() {
        int[][] state = {{1, 2, 3, 4},
                         {5, 6, 7, 8},
                         {9, 10, 11, 12},
                         {13, 14, 0, 15}};
        
        assertFalse(b.matchesState(state));
    }
}
