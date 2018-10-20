
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import puzzle.fifteenpuzzlesolver.Board;
import puzzle.fifteenpuzzlesolver.Solver;

/**
 *
 * @author Matias Wargelin
 */
@Ignore("These tests take a lot of time: do not ignore when you want to test performance")
public class PerformanceTest {
	
	public PerformanceTest() {
	}

	public static long[] test(int n) {
        long[] times = new long[100];
        int j = 0;

        for (int i = 0; i < 100; i++) {
            Board board = new Board(n);
            Solver solver = new Solver();
            board.Shuffle();
            
            long start = System.currentTimeMillis();
            solver.solve(board, true);
            long end = System.currentTimeMillis();
            long diff = end - start;
            times[j] = diff;
            j++;
        }
        
        long sum = 0;
        for (long l : times) {
            System.out.println(l);
            sum += l;
        }
        
        System.out.println("AVERAGE " + (n*n-1) + "-puzzle:" + (sum / 100.0));
		Arrays.sort(times);
		System.out.println("MEDIAN " + (n*n-1) + "-puzzle: " + times[times.length / 2]);
        return times;
    }
	
	@Test
	public void performanceTest8Board() {
		long[] times = test(3);
		assertEquals(100, times.length);
	}
	
	@Test
	public void performanceTest15Board() {
		long[] times = test(4);
		assertEquals(100, times.length);
	}
}
