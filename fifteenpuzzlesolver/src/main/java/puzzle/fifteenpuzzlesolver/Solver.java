
package puzzle.fifteenpuzzlesolver;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * A Solver for {@code Board}. Uses A*-algorithm to find the optimal path 
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
     * Solves a {@code Board}. Solved the given {@code Board} and prints the result. 
     * Does nothing if the given {@code Board} is already solved.
     * @param board A {@code Board} to solve
     * @return A new {@code Board} which is in a solved state. The solution is traceable
     * by using the {@code Board}'s getPreviousBoard-method in a loop until the 
     * previous borad is {@code null}.
     */
    public Board solve(Board board) {
        if(board.isSolved()) return board;
        
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

        return printSolution();
    }
    
    private Board printSolution() {
        Board print = null;
        while(!stack.empty()) {
            print = stack.pop();
            System.out.println(print.toString());
            System.out.println("\n--->\n");
        }
        System.out.println("SOLVED");
        
        return print;
    }
    
}
