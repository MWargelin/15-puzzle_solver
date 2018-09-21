
package puzzle.fifteenpuzzlesolver;

import java.util.Scanner;

/**
 *
 * @author Matias Wargelin
 */
public class Fifteenpuzzlesolver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int N = 4;
        Board b = new Board(N);
        Solver solver = new Solver();
        
        Scanner s = new Scanner(System.in);
        
        System.out.println("Welcome to play " + (N * N - 1) +"-puzzle!");
        System.out.println("");
        System.out.println("Enter:");
        System.out.println("- w, a, s, d to move");
        System.out.println("- shuffle to shuffle");
        System.out.println("- solve to solve");
        System.out.println("- x to quit");
        System.out.println("");
        
        boolean goOn = true;
        while(goOn) {
            System.out.println(b.toString());
            System.out.println("");
            System.out.println("- - - - -");
            String cmd = s.nextLine();
            System.out.println("- - - - -");
            System.out.println("");
            
            switch(cmd) {
                case "x": goOn = false; break;
                case "shuffle": b.Shuffle(); break;
                case "solve": {
                    System.out.println("Solve automatically...");
                    System.out.println("");
                    solver.solve(b);
                    goOn = false;
                    break;
                }
                default: b.moveTiles(cmd);
            }

        }
        
        System.out.println("Thank you for playing!");
    }
    
}
