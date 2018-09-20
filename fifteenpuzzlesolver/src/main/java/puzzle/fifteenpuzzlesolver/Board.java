
package puzzle.fifteenpuzzlesolver;

import java.util.Random;

 /**
 * Class for the game board of the puzzle.
 * 
 * @author Matias Wargelin
 */
public class Board implements Comparable<Board>{
    private final int N;
    private int[][] board;
    private int movesSoFar;
    
    public Board(int n){
        this.N = n;
        this.board = new int[N][N];
        this.movesSoFar = 0;
        initBoard();
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
    
    public void setBoard(int[][] newBoard) {
        this.board = newBoard;
    }

    public int getMovesSoFar() {
        return movesSoFar;
    }
    
    /**
     * Tells if this <code>Board</code> is in a solved state. A <code>Board</code> 
     * is solved if read from left to right, top to bottom it has numbers 1-15 in order and 
     * the empty space as the last tile.
     * @return <code>true</code> if this <code>Board</code> is in a solved state, 
     * otherwise <code>false</code>
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
            case "w":  
                if(!moveUp()) {
                    System.out.println("Can't move any tile up");
                    return false;
                } break;
            
            case "a":
                if(!moveLeft()) {
                    System.out.println("Can't move any tile left");
                    return false;
                } break;
            
            case "s":
                if(!moveDown()) {
                    System.out.println("Can't move any tile down");
                    return false;
                } break;
                
            case "d":
                if(!moveRight()) {
                    System.out.println("Can't move any tile right");
                    return false;
                } break;
                
            default: System.out.println("Not a legal move"); return false;
        }
        
        movesSoFar++;
        return true;
    }
    
    /**
     * Shuffles the board with 100 random-generated moves.
     */
    public void Shuffle() {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int direction = r.nextInt(4);
            
            int[] emptyTile = this.findTile(0);
            int emptyTileY = emptyTile[0];
            int emptyTileX = emptyTile[1];
            
            switch(direction) {
                case 0: moveUp(); break;
                case 1: moveLeft(); break;
                case 2: moveDown(); break;
                case 3: moveRight(); break;
            }
        }
        
        if(isSolved()) Shuffle();
        movesSoFar = 0;
    }
    
    private boolean moveUp() {
        int[] emptyTile = this.findTile(0);
        int emptyTileY = emptyTile[0];
        int emptyTileX = emptyTile[1];
        
        if(emptyTileY >= N-1) return false;
        
        //Switch tiles
        board[emptyTileY][emptyTileX] = board[emptyTileY+1][emptyTileX];
        board[emptyTileY+1][emptyTileX] = 0;

        //Update the position of empty space
        emptyTileY++;
        
        return true;
    }
    
    private boolean moveDown() {
        int[] emptyTile = this.findTile(0);
        int emptyTileY = emptyTile[0];
        int emptyTileX = emptyTile[1];
        
        if(emptyTileY <= 0) return false;
        
        //Switch tiles
        board[emptyTileY][emptyTileX] = board[emptyTileY-1][emptyTileX];
        board[emptyTileY-1][emptyTileX] = 0;

        //Update the position of empty space
        emptyTileY--;
        
        return true;
    }
    
    private boolean moveLeft() {
        int[] emptyTile = this.findTile(0);
        int emptyTileY = emptyTile[0];
        int emptyTileX = emptyTile[1];
        
        if(emptyTileX >= N-1) return false;

        //Switch tiles
        board[emptyTileY][emptyTileX] = board[emptyTileY][emptyTileX+1];
        board[emptyTileY][emptyTileX+1] = 0;

        //Update the position of empty space
        emptyTileX++;
        
        return true;
    }
    
    private boolean moveRight() {
        int[] emptyTile = this.findTile(0);
        int emptyTileY = emptyTile[0];
        int emptyTileX = emptyTile[1];
        
        if(emptyTileX <= 0) return false;

        //Switch tiles
        board[emptyTileY][emptyTileX] = board[emptyTileY][emptyTileX-1];
        board[emptyTileY][emptyTileX-1] = 0;

        //Update the position of empty space
        emptyTileX--;
        
        return true;
    }
    
    /**
     * Returns the Manhattan distance of this <code>Board</code>. Manhattan distance of 
     * a 15-puzzle is the sum of each tile's distance from its place in a solved 
     * state. The distance of the empty space from its position is not added in the sum.
     * @return The Manhattan distance of this <code>Board</code>.
     */
    public int manhattanDistance() {
        int manhattanDistance = 0;
        
        for (int k = 1; k < N*N; k++) {
            
            //Where tile k should be:
            int i1 = (k-1) / N;
            int j1 = (k-1) % N;
            
            //Where tile k is now:
            int[] tilePosition = findTile(k);
            int i2 = tilePosition[0];
            int j2 = tilePosition[1];
            
            manhattanDistance += Math.abs(i1 - i2) + Math.abs(j1 - j2);
        }
        
        return manhattanDistance;
    }
    
    /**
     * Gives the indexes <code>i</code> and <code>j</code> of a wanted tile on this 
     * <code>Board</code> in an integer table of length 2. Index <code>i</code> is stored in 
     * <code>tilePosition[0]</code> and <code>j</code> in <code>tilePosition[1]</code>
     * @param tile - number of the wanted tile
     * @return indexes of the tile in <code>int[]</code>. Returns <code>null</code>
     * if there isn't such a tile on the board.
     */
    public int[] findTile(int tile) {
        if(tile >= N*N) return null;
        
        int[] tilePosition = new int[2];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == tile) {
                    tilePosition[0] = i;
                    tilePosition[1] = j;
                }
            }
        }
        
        return tilePosition;
    }
    
    public int distanceFromSolved() {
        return movesSoFar + this.manhattanDistance();
    }
    
    public Board copy() {
        Board copy = new Board(N);
        copy.setBoard(board);
        return copy;
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
            if(i != board.length - 1) sb.append("\n");
        }
        
        return sb.toString();
    }

    @Override
    public int compareTo(Board other) {
        return this.distanceFromSolved() - other.distanceFromSolved();
    }
}
