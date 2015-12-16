import Data.DataDefs;
import Data.Helpers;
import Data.Position;
import Solver.Solve;

/**
 * Created by Prayansh on 2015-12-15.
 */
public class MazeSolver {
    public static void main(String[] args) {
        try {
            Solve solver = new Solve(Helpers.produceList(DataDefs.M3));
            System.out.println("Path Taken:" + solver.solveWithRecursion(DataDefs.P3));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("PROGRAM TERMINATED");
        }
    }
}
