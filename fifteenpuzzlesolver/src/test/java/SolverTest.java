
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import puzzle.fifteenpuzzlesolver.Board;
import puzzle.fifteenpuzzlesolver.Solver;

/**
 *
 * @author Matias Wargelin
 */
public class SolverTest {
    Solver s;
    
    public SolverTest() {
    }
    
    @Before
    public void setUp() {
        s = new Solver();
    }

    @Test
    public void solvesABoardOneMoveAwayFromSolved() {
        Board b = new Board(4);
        b.moveTiles("d");
        
        Board solved = s.solve(b);
        
        assertTrue(solved.isSolved());
    }
    
    @Test
    public void solvesAShuffled8Board() {
        Board b = new Board(3);
        b.Shuffle();
        
        Board solved = s.solve(b);
        
        assertTrue(solved.isSolved());
    }
    
    @Test
    public void solvesAShuffled15Board() {
        Board b = new Board(4);
        b.Shuffle();
        
        Board solved = s.solve(b);
        
        assertTrue(solved.isSolved());
    }
}
