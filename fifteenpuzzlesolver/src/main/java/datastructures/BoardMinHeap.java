
package datastructures;

import puzzle.fifteenpuzzlesolver.Board;

/**
 * Minimum heap to store {@code Board}s.
 * @author Matias Wargelin
 */
public class BoardMinHeap {
    private Board[] heap;
    private int heapSize;
    
    public BoardMinHeap() {
        heap = new Board[100];
        this.heapSize = 0;
    }
    
    /**
     * Inserts a {@code Board} to the heap.
     * @param board {@code Board} to insert to the heap
     */
    public void insert(Board board) {
        if(heapSize >= heap.length) {
            this.doubleSizeOfHeap();
        }
        
        int i = heapSize;
        while(i > 0 && heap[parent(i)].compareTo(board) > 0) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = board;
        
        heapSize++;
    }
    
    private void doubleSizeOfHeap() {
        Board[] newHeap = new Board[heap.length * 2];
        
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        
        this.heap = newHeap;
    }
    
    /**
     * Returns a {@code Board} from the top of the heap.
     * @return the {@code Board} from the heap that is closest to the solved state. 
     * Returns {@code null} if the heap is empty.
     */
    public Board poll() {
        if(heapSize <= 0) return null;
        
        Board min = heap[0];
        heap[0] = heap[heapSize - 1];
        heap[heapSize - 1] = null;  //Eliminate obsolete reference
        heapSize--;
        heapify(0);
                
        return min;
    }
    
    private void heapify(int i) {
        while(true) {
            int l = leftChild(i);
            int r = rightChild(i);

            if(r <= heapSize - 1) {
                Board smallest = heap[r];
                int smallestIndex = r;
                if(heap[l].compareTo(heap[r]) < 0) {
                    smallest = heap[l];
                    smallestIndex = l;
                }

                if(smallest.compareTo(heap[i]) < 0) {
                    Board temp = heap[i];
                    heap[i] = smallest;
                    heap[smallestIndex] = temp;
                    i = smallestIndex;
                    continue;
                }                
            }
            break;
        }
        int l = leftChild(i);
        if (l == heapSize - 1 && heap[l].compareTo(heap[i]) < 0) {
            Board temp = heap[i];
            heap[i] = heap[l];
            heap[l] = temp;
        }
    }
    
    private int parent(int i) {
        return (i + 1) / 2 - 1;
    }
    
    private int leftChild(int i) {
        return (i + 1) * 2 - 1;
    }
    
    private int rightChild(int i) {
        return (i + 1) * 2 ;
    }

    public int getHeapSize() {
        return heapSize;
    }
    
    public void printHeap() {
        for (int i = 0; i < heapSize; i++) {
            System.out.println(heap[i].toString());  
            System.out.println("");
        }
    }
}
