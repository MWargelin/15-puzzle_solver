

import datastructures.BoardMinHeap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void parentTest() {
        assertEquals(0, minHeap.parent(1));
        assertEquals(0, minHeap.parent(2));
        assertEquals(1, minHeap.parent(3));
        assertEquals(1, minHeap.parent(4));
        assertEquals(2, minHeap.parent(5));
        assertEquals(2, minHeap.parent(6));
        assertEquals(3, minHeap.parent(7));
        assertEquals(3, minHeap.parent(8));
        assertEquals(4, minHeap.parent(9));
        assertEquals(4, minHeap.parent(10));
    }
    
    @Test
    public void childTests() {
        assertEquals(1, minHeap.leftChild(0));
        assertEquals(2, minHeap.rightChild(0));
        
        assertEquals(7, minHeap.leftChild(3));
        assertEquals(8, minHeap.rightChild(3));
    }
    
}
