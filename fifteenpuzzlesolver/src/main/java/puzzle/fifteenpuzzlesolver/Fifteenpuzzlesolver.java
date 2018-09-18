
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
        
        Scanner s = new Scanner(System.in);
        
        System.out.println("Welcome to play " + (N * N - 1) +"-puzzle!");
        System.out.println("Enter w, a, s, d to move or x to quit");
        System.out.println("");
        
        b.Shuffle();
               
        while(true) {
            System.out.println(b.toString());
            System.out.println("");
            System.out.println("- - - - -");
            String d = s.nextLine();
            System.out.println("- - - - -");
            System.out.println("");
            
            if(d.equals("x")) break;
            
            b.moveTiles(d);
        }
        
        System.out.println("Thank you for playing!");
    }
    
}
