
package puzzle.fifteenpuzzlesolver;

import java.util.Random;

/**
 * Class for the game board of the puzzle.
 * 
 * @author Matias Wargelin
 */
public class Board {
    private final int N;
    private int[][] board;
    private int emptyTileX;
    private int emptyTileY;
    
    public Board(int n){
        this.N = n;
        this.board = new int[N][N];
        initBoard();
        this.emptyTileY = N-1;
        this.emptyTileX = N-1;       
    }
    
    private void initBoard(){
        int number = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = number;
                number++;
            }  
        }
        
        //Switch last tile to empty space
        board[N-1][N-1] = 0;
    }
    
    /**
     * @return The length of the side of this <code>Board</code>.
     */
    public int getN() {
        return N;
    }
    
    /**
     * Tells if this <code>Board</code> is in a solved state. A <code>Board</code> 
     * is solved if read from left to right, top to bottom it has numbers 1-15 in order and 
     * the empty space as the last tile.
     * @return <code>true</code> if this <code>Board</code> is in a solved state, 
     * otherwise <code>false<code/>
     */
    public boolean isSolved(){
        int expected = 1;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == 0 && expected == N * N) break;
                if(board[i][j] != expected)return false;
                expected++;
            }
        }
        return true;
    }
    
    /**
     * Moves a tile to the empty space if possible. 
     * @param direction the direction where a numbered tile moves to (not the direction 
     * where the empty space goes to!)
     * <br><br>
     * Legal moves:
     * <ul>
     * <li>w - up</li>
     * <li>a - left</li>
     * <li>s - down</li>
     * <li>d - right</li>
     * </ul>
     * @return <code>true</code> if the move is valid, otherwise <code>false</code>.
     */
    public boolean moveTiles(String direction) {
        switch(direction){
            case "w": if(!moveUp()) System.out.println("Can't move any tile up"); break;
            case "a": if(!moveLeft()) System.out.println("Can't move any tile left"); break;
            case "s": if(!moveDown()) System.out.println("Can't move any tile down"); break;
            case "d": if(!moveRight()) System.out.println("Can't move any tile right"); break;
            default: System.out.println("Not a legal move"); return false;
        }
        return true;
    }
    
    /**
     * Shuffles the board with 100 random-generated moves.
     */
    public void Shuffle() {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int direction = r.nextInt(4);
            
            switch(direction) {
                case 0: moveUp(); break;
                case 1: moveLeft(); break;
                case 2: moveDown(); break;
                case 3: moveRight(); break;
            }
        }
        
        if(isSolved()) Shuffle();
    }
    
    private boolean moveUp() {
        if(emptyTileY >= N-1) return false;
        
        //Switch tiles
        board[emptyTileY][emptyTileX] = board[emptyTileY+1][emptyTileX];
        board[emptyTileY+1][emptyTileX] = 0;

        //Update the position of empty space
        emptyTileY++;
        
        return true;
    }
    
    private boolean moveDown() {
        if(emptyTileY <= 0) return false;
        
        //Switch tiles
        board[emptyTileY][emptyTileX] = board[emptyTileY-1][emptyTileX];
        board[emptyTileY-1][emptyTileX] = 0;

        //Update the position of empty space
        emptyTileY--;
        
        return true;
    }
    
    private boolean moveLeft() {
        if(emptyTileX >= N-1) return false;

        //Switch tiles
        board[emptyTileY][emptyTileX] = board[emptyTileY][emptyTileX+1];
        board[emptyTileY][emptyTileX+1] = 0;

        //Update the position of empty space
        emptyTileX++;
        
        return true;
    }
    
    private boolean moveRight() {
        if(emptyTileX <= 0) return false;

        //Switch tiles
        board[emptyTileY][emptyTileX] = board[emptyTileY][emptyTileX-1];
        board[emptyTileY][emptyTileX-1] = 0;

        //Update the position of empty space
        emptyTileX--;
        
        return true;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                sb.append("[");
                
                if(board[i][j] == 0) sb.append("x ");
                else sb.append(String.format("%-2d", board[i][j]));
                
                sb.append("]");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
