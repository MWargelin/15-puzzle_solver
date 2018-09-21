
package puzzle.fifteenpuzzlesolver;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * A Solver for <code>Board</code>. Uses A*-algorithm to find the optimal path 
 * from any permutation of the tiles to the solved state.
 * @author Matias Wargelin
 */
public class Solver {
    private Queue<Board> minHeap;
    private Stack<Board> stack;
    
    public Solver() {
        this.minHeap = new PriorityQueue<>();
        this.stack = new Stack<>();
    }
    
    /**
     * Uses A*-star algorithm to solve given <code>Board</code>.
     * @param board - <code>Board</code> to solve.
     */
    public void solve(Board board) {
        while(!board.isSolved()) {
            
            for (int i = 0; i < 4; i++) {
                Board copy = board.copy();
                copy.setPreviousBoard(board);
                boolean isLegalMove = false;

                switch(i) {
                    case 0: isLegalMove = copy.moveTiles("w"); break;
                    case 1: isLegalMove = copy.moveTiles("a"); break;
                    case 2: isLegalMove = copy.moveTiles("s"); break;
                    case 3: isLegalMove = copy.moveTiles("d"); break;
                }
                
                if(isLegalMove) minHeap.add(copy);
            }
            
            board = minHeap.poll();
        }

        while(board != null) {
            stack.add(board);
            board = board.getPreviousBoard();
        }

        printSolution();
    }
    
    private void printSolution() {
        while(!stack.empty()) {
            Board print = stack.pop();
            System.out.println(print.toString());
            System.out.println("\n--->\n");
        }
        System.out.println("SOLVED");
    }
    
}
