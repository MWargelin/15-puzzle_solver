
import datastructures.BoardStack;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import puzzle.fifteenpuzzlesolver.Board;

/**
 *
 * @author Matias Wargelin
 */
public class BoardStackTest {
    BoardStack bs;
    
    public BoardStackTest() {
    }
    
    @Before
    public void setUp() {
        bs = new BoardStack();
    }

    @Test
    public void isEmptyReturnsTrueIfStackIsEmpty() {
        assertTrue(bs.isEmpty());
    }
    
    @Test
    public void isEmptyReturnsFalseIfStackIsNotEmpty() {
        bs.push(new Board(4));
        assertFalse(bs.isEmpty());
    }
    
    @Test
    public void pushesAndPopsABoard() {
        Board b = new Board(4);
        bs.push(b);
        Board b2 = bs.pop();
        
        assertTrue(b2.equals(b));
    }
    
    @Test
    public void returnsNullIfAnEmptyStackIsPopped() {
        assertEquals(null, bs.pop());
    }
    
    @Test
    public void sizeReturnsTheRightSize() {
        bs.push(new Board(4));
        bs.push(new Board(4));
        bs.push(new Board(4));
        
        assertEquals(3, bs.size());
    }
    
    @Test
    public void stackOverflowIsHandled() {
        for (int i = 0; i < 100; i++) {
            bs.push(new Board(4));
        }
        
        assertEquals(100, bs.size());
    }
}
