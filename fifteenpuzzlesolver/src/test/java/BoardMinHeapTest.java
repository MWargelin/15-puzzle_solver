

import datastructures.BoardMinHeap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import puzzle.fifteenpuzzlesolver.Board;

/**
 *
 * @author Matias Wargelin
 */
public class BoardMinHeapTest {
    BoardMinHeap minHeap;
    
    public BoardMinHeapTest() {
    }
    
    @Before
    public void setUp() {
        minHeap = new BoardMinHeap();
    }

    @Test
    public void insertsOneBoard() {
        assertEquals(0, minHeap.getHeapSize());
        minHeap.insert(new Board(4));
        assertEquals(1, minHeap.getHeapSize());
    }
    
    @Test
    public void insertKeepsBoardsInOrder() {
        Board b1 = new Board(4);
        Board b2 = new Board(4);
        Board b3 = new Board(4);
        Board b4 = new Board(4);
        
        //Boards are in an increasingly worse state
        b2.moveTiles("s");
        
        b3.moveTiles("s");
        b3.moveTiles("s");
        
        b4.moveTiles("s");
        b4.moveTiles("s");
        b4.moveTiles("s");
        
        //insert from worst to best
        minHeap.insert(b4);
        minHeap.insert(b3);
        minHeap.insert(b2);
        minHeap.insert(b1);
        
        System.out.println("INSERT KEEPS BOARDS IN ORDER TEST:");
        minHeap.printHeap();
        
        Board board = minHeap.poll();
        assertTrue(board.isSolved());
    }
    
    @Test
    public void expandsHeapIfABoardIsInsertedInFullHeap() {
        for (int i = 0; i < 1000; i++) {
            minHeap.insert(new Board(4));  
        }
        
        assertEquals(1000, minHeap.getHeapSize());
    }
    
    @Test
    public void pollFromAnEmptyHeapReturnsNull() {
        assertNull(minHeap.poll());
    }
    
    @Test
    public void pollReturnsTheBoardFromTheTopOfTheHeap() {
        Board b1 = new Board(4);
        Board b2 = new Board(4);
        b2.moveTiles("s");
        
        minHeap.insert(b2);
        minHeap.insert(b1);
        
        Board board = minHeap.poll();
        assertTrue(board.equals(b1));
    }
    
    @Test
    public void heapifyWorksIfTheresOnlyALeftChild() {
        Board b1 = new Board(4);
        Board b2 = new Board(4);
        Board b3 = new Board(4);
        
        b2.moveTiles("s");
        b3.moveTiles("s");
        b3.moveTiles("s");
        
        minHeap.insert(b1);
        minHeap.insert(b2);
        minHeap.insert(b3);
        
        minHeap.poll();
        Board b = minHeap.poll();
        assertTrue(b.equals(b2));
    }
    
}
