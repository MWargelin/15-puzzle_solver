
package puzzle.fifteenpuzzlesolver;

import datastructures.BoardMinHeap;
import datastructures.BoardStack;

/**
 * A Solver for {@code Board}. Uses A*-algorithm to find the optimal path 
 * from any permutation of the tiles to the solved state.
 * @author Matias Wargelin
 */
public class Solver {
    private BoardMinHeap minHeap;
    private BoardStack stack;
    
    public Solver() {
        this.minHeap = new BoardMinHeap();
        this.stack = new BoardStack();
    }
    
    /**
     * Solves a {@code Board}. Solves the given {@code Board} and prints the result. 
     * Does nothing if the given {@code Board} is already solved.
     * @param board {@code Board} to solve
     * @return New {@code Board} which is in a solved state. The solution is traceable
     * by using the {@code Board}'s getPreviousBoard-method in a loop until the 
     * previous board is {@code null}.
     */
    public Board solve(Board board) {
        if(board.isSolved()) return board;
        
        while(!board.isSolved()) {
            
            for (int i = 0; i < 4; i++) {
                Board copy = board.copy();
                copy.setPreviousBoard(board);
                int[][] previousState = copy.getPreviousBoard().getBoard();
                boolean isLegalMove = false;

                switch(i) {
                    case 0: isLegalMove = copy.moveTiles("w"); break;
                    case 1: isLegalMove = copy.moveTiles("a"); break;
                    case 2: isLegalMove = copy.moveTiles("s"); break;
                    case 3: isLegalMove = copy.moveTiles("d"); break;
                }
                
                if(isLegalMove && !copy.matchesState(previousState)) minHeap.insert(copy);
            }
            
            board = minHeap.poll();
        }

        while(board != null) {
            stack.push(board);
            board = board.getPreviousBoard();
        }

        return printSolution();
    }
    
    private Board printSolution() {
        Board print = null;
        while(!stack.isEmpty()) {
            print = stack.pop();
            System.out.println(print.toString());
            System.out.println("\n--->\n");
        }
        System.out.println("SOLVED");
        
        return print;
    }
    
}
