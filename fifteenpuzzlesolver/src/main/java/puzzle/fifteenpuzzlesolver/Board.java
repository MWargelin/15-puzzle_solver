
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
    private Board previousBoard;
    
	/**
	 * Game board of the puzzle.
	 * @param n the length of the side of the board
	 */
    public Board(int n){
        this.N = n;
        this.board = new int[N][N];
        this.movesSoFar = 0;
        previousBoard = null;
        initBoard();
    }
    
	/**
	 * Initializes the {@code Board} to have all the numbers in order from left to right, 
	 * top to bottom and the empty space as the last tile.
	 */
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
     * @return The length of the side of this {@code Board}
     */
    public int getN() {
        return N;
    }
    
    /**
     * @return the state of this {@code Board}, i.e. the order of the tiles on the board
     */
    public int[][] getBoard() {
        return this.board;
    }
    
    /**
     * @param newBoard - new order of the {@code Board} in int[][] array
     */
    public void setBoard(int[][] newBoard) {
        this.board = newBoard;
    }
    
    /**
     * @return the number of moves it took to get to this state of the {@code Board}
     */
    public int getMovesSoFar() {
        return movesSoFar;
    }
    
    private void setMovesSoFar(int movesSoFar) {
        this.movesSoFar = movesSoFar;
    }
    
    /**
     * @return the state this {@code Board} was in before the last move
     */
    public Board getPreviousBoard() {
        return previousBoard;
    }

    /**
     * Sets a previous state for this {@code Board} before its last move.
     * @param previous the previous state
     */
    public void setPreviousBoard(Board previous) {
        this.previousBoard = previous;
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
            case "w": if(!moveUp()) return false; break;           
            case "a": if(!moveLeft()) return false; break;            
            case "s": if(!moveDown()) return false; break;               
            case "d": if(!moveRight()) return false; break;               
            default: return false;
        }
        
        movesSoFar++;
        return true;
    }
    
    private boolean moveUp() {
        int[] emptyTile = this.findTile(0);
        int emptyTileY = emptyTile[0];
        int emptyTileX = emptyTile[1];
        
        if(emptyTileY >= N-1) return false;
        
        //Switch tiles
        board[emptyTileY][emptyTileX] = board[emptyTileY+1][emptyTileX];
        board[emptyTileY+1][emptyTileX] = 0;
        
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
        
        return true;
    }
   
    /**
     * Tells if this {@code Board} is in a solved state. A {@code Board} 
     * is solved if read from left to right, top to bottom it has numbers 1-15 in order and 
     * the empty space as the last tile.
     * @return {@code true} if this {@code Board} is in a solved state, 
     * otherwise {@code false}
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
        movesSoFar = 0;
    }
    
    /**
     * The Manhattan distance of this {@code Board}. Manhattan distance of 
     * a 15-puzzle is the sum of each tile's distance from its place in a solved 
     * state. The distance of the empty space from its position is not added in the sum.
     * @return The Manhattan distance of this {@code Board}
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
     * Returns the number of linear conflicts on the {@code Board}. Two tiles
     * are in a linear conflict if both of them are in their home row/column 
     * but they are on each other's way. More precisely, tiles x and y are in a linear
     * conflict if:
     * <ul>
     * <li>x and y are on same row/column AND</li>
     * <li>the home row/column of both x and y is this row/column AND</li>
     * <li>x is on the left side of y, even though it's supposed to be on the right side of y (rows) OR</li>
     * <li>x is on top of y, even though it's supposed to be below of y (columns)</li>
     * </ul>
     * @return the number of linear conflicts on a {@code Board}
     */
    public int linearConflicts() {       
        Board solvedBoard = new Board(N);
        int conflicts = 0;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int tile1 = board[i][j];
                
                //empty tile should not be tested
                if(tile1 == 0) continue;
                
                //find out where the tile should be
                int[] whereTile1ShouldBe = solvedBoard.findTile(tile1);
                
                //if the tile is on its home row:
                if(i == whereTile1ShouldBe[0]) {

                    //check tiles on its right side
                    for (int k = j; k < board.length; k++) {
                        int tile2 = board[i][k];
                        
                        //empty tile should not be tested
                        if(tile1 == 0) continue;
                        
                        //if a tile on the right side of tile1 is on its home row,
                        //but should be on the left side, a conflict is found.
                        //add two conflicts, because we won't spot the conflict when 
                        //we get to tile2 the next time as we are only checking tiles on the right
                        int[] whereTile2ShouldBe = solvedBoard.findTile(tile2);
                        if(i == whereTile2ShouldBe[0] && whereTile2ShouldBe[1] < whereTile1ShouldBe[1]) {
                            conflicts += 2;
                        }
                    }
                }
                
                //if the tile is on its home column:
                if(j == whereTile1ShouldBe[1]) {
                    
                    //check tiles under it
                    for (int k = i; k < board.length; k++) {
                        int tile2 = board[k][j];
                        
                        //etc., similar idea as checking rows
                        if(tile1 == 0) continue;
                        
                        int[] whereTile2ShouldBe = solvedBoard.findTile(tile2);
                        if(j == whereTile2ShouldBe[1] && whereTile2ShouldBe[0] < whereTile1ShouldBe[0]) {
                            conflicts += 2;
                        }
                    }
                }
                   
            }
            
        }
        
        return conflicts;
    }
    
    /**
     * Gives the indexes {@code i} and {@code j} of a wanted tile on this 
     * {@code Board} in an integer table of length 2. Index {@code i} is stored in 
     * {@code tilePosition[0]} and {@code j} in {@code tilePosition[1]}
     * @param tile number of the wanted tile
     * @return indexes of the tile in {@code int[]}. Returns {@code null}
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
    
    /**
     * Approximation of this {@code Board}'s distance from the solved 
     * state.
     * @return approximation of this {@code Board}'s distance from the solved 
     * state given by the heuristic function of the A*-algorithm
     */
    public int distanceFromSolved() {
        return this.movesSoFar + this.manhattanDistance() + this.linearConflicts();
    }
    
    /**
     * Returns a new {@code Board} that has the same values as this one.
     * @return a copy of this {@code Board}.
     */
    public Board copy() {
        Board copy = new Board(N);
        
        int[][] copyBoard = new int[N][N];
        for (int i = 0; i < copyBoard.length; i++) {
            for (int j = 0; j < copyBoard.length; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }

        copy.setBoard(copyBoard);
        copy.setMovesSoFar(movesSoFar);
        return copy;
    }
    
    /**
     * Tells if the given state matches the state of this {@code Board}
     * @param state the state to compare with this {@code Board}'s state
     * @return {@code true} if parameter {@code state} has all the same numbers
     * in the same order as this {@code Board}, otherwise {@code false}
     */
    public boolean matchesState(int[][] state) {       
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] != state[i][j]) return false;
                
            }
        }
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
            if(i != board.length - 1) sb.append("\n");
        }
        
        return sb.toString();
    }

    @Override
    public int compareTo(Board other) {
        return this.distanceFromSolved() - other.distanceFromSolved();
    }
}