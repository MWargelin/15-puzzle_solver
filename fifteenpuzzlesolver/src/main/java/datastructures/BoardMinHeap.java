
package datastructures;

import puzzle.fifteenpuzzlesolver.Board;

/**
 * Minimum heap to store {@code Board}s.
 * @author Matias Wargelin
 */
public class BoardMinHeap {
    private Board[] heap;
    
    public BoardMinHeap() {
        heap = new Board[100];
    }
    
    public void insert(Board board) {
        // if(heap) on täysi tuplaa koko
    }
    
    public void heapify(int i) {
        
    }
    
    public int parent(int i) {
        //TODO i = 0
        return (i + 1) / 2 - 1;
    }
    
    public int leftChild(int i) {
        //TODO no children
        return (i + 1) * 2 - 1;
    }
    
    public int rightChild(int i) {
        //TODO no children
        return (i + 1) * 2 ;
    }
}
