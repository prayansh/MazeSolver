import Data.Helpers;
import Generator.BinaryTree;

import java.util.Scanner;

/**
 * Created by Prayansh on 2015-12-15.
 */
public class MazeSolver {
    public static void main(String[] args) {
        try {
//            Solve solver = new Solve(Helpers.produceList(DataDefs.M3));
//            System.out.println("Path Taken:" + solver.solveWithRecursion(DataDefs.P3));
//            System.out.println("Path Taken:" + solver.solveWithLoop(DataDefs.P3));
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Size : ");
            int size = sc.nextInt();
            BinaryTree bt = new BinaryTree(size);
            bt.makeMaze();
            Helpers.renderMaze(bt.getMaze());
            System.out.println("++++++++++++++++++++++");

//            System.out.println("=============");
//            Helpers.renderMaze(solver.getMAZE());
//            System.out.println("=============");
//            Helpers.renderMazeWithPath(solver.getMAZE(), solver.solveWithRecursion(DataDefs.P4));
//            System.out.println("=============");
//            solver.clear();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("PROGRAM TERMINATED");
        }
    }
}
