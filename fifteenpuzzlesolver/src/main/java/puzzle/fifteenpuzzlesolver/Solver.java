
package puzzle.fifteenpuzzlesolver;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A Solver for <code>Board</code>. Uses A*-algorithm to find the optimal path 
 * from any permutation of the tiles to the solved state.
 * @author Matias Wargelin
 */
public class Solver {
    private Queue<Board> minHeap;
    
    public Solver() {
        this.minHeap = new PriorityQueue<>();
    }
    
    public void solve(Board board) {
        //Find neighbours and add to minHeap
        for (int i = 0; i < 4; i++) {
            
            
        }
    }
    
}
