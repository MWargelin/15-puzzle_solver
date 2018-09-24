
package datastructures;

import puzzle.fifteenpuzzlesolver.Board;

/**
 * Stack to store {@code Board}s. 
 * @author Matias Wargelin
 */
public class BoardStack {
    private Board[] stack;
    private int top;

    public BoardStack() {
        this.stack = new Board[80]; //Maximum number of moves in a solution for 15-puzzle is 80
        this.top = -1;
    }
    
    /**
     * Tells if this {@code BoardStack} is empty.
     * @return {@code true} if this {@code BoardStack} is empty, otherwise {@code false}
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * Pushes a {@code Board} on top of this {@code BoardStack}.
     * @param board the {@code Board} to push into the stack
     */
    public void push(Board board) {
        top++;
        if(top >= stack.length) {
            this.doubleTheSizeOfStack();
        }
        stack[top] = board;
    }
    
    /**
     * Doubles the size of this stack if more elements are pushed in it 
     * than the initial size allows
     */
    private void doubleTheSizeOfStack() {
        Board[] newStack = new Board[stack.length * 2];
        
        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }
        
        stack = newStack;
    }
    
    /**
     * Pop a {@code Board} from the top of this {@code BoardStack}.
     * @return the {@code Board} on top of the stack. Returns {@code null} if the 
     * stack is empty.
     */
    public Board pop() {
        if(this.isEmpty()) return null;
        
        Board board = stack[top];
        top--;
        return board;
    }
    
    /**
     * 
     * @return the number of {@code Board}s that are stored in the stack
     */
    public int size() {
        return top + 1;
    }
    
}
