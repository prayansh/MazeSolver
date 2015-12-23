package Tests;

/**
 * Created by Prayansh on 2015-12-23.
 */

import Data.DataDefs;
import Data.Helpers;
import Solver.Solve;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class SolverTests {

    Solve solver1;
    Solve solver2;

    @Before
    public void setUp() throws Exception {
        solver1 = new Solve(Helpers.produceList(DataDefs.M5));
        solver2 = new Solve(Helpers.produceList(DataDefs.M5));
    }

    @Test
    public void testRecursion() {
        long startTime = System.currentTimeMillis();
        solver1.solveWithRecursion(DataDefs.P5);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }

    @Test
    public void testLoop() {
        long startTime = System.currentTimeMillis();
        solver1.solveWithLoop(DataDefs.P5);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }
}
