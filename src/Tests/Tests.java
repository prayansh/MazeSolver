package Tests;

import Data.DataDefs;
import Data.Helpers;
import Data.Position;
import Solver.Solve;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Prayansh on 2015-12-15.
 * Tests for Position Data Definition
 */


public class Tests {

    public Position P1 = new Position();
    public Position P2 = new Position(10, 3);
    public Position P3 = new Position(3, 10);
    public Position P4 = new Position(10, 3);
    public Position P5 = new Position(0, 0);

    @Test
    public void testEquals() throws Exception {
        assertFalse(P1.equals("st"));
        assertFalse(P2.equals("ab"));
        assertFalse(P3.equals("c"));
        assertFalse(P4.equals(1));
        assertFalse(P4.equals(P3));
        assertFalse(P3.equals(P4));
        assertFalse(P1.equals(P4));
        assertTrue(P2.equals(P4));
        assertTrue(P4.equals(P2));
        assertTrue(P5.equals(P1));
    }

    @Test
    public void testMapUsingLength() throws Exception {
        assertTrue(new Position(0, 0).equals(P1.mapUsingLength(0, 4)));
        assertTrue(new Position().equals(P1.mapUsingLength(0, 2)));
        assertEquals(new Position(1, 0), P1.mapUsingLength(1, 9));
        assertTrue(new Position(0, 1).equals(P1.mapUsingLength(4, 4)));
        assertTrue(new Position(1, 1).equals(P1.mapUsingLength(5, 4)));
        assertEquals(new Position(3, 3), (P1.mapUsingLength(15, 4)));
    }

    @Test
    public void testMapToLength() throws Exception {
        assertEquals(P1.mapToLength(4), 0);
        assertEquals(P2.mapToLength(12), 46);
        assertEquals(P3.mapToLength(4), 43);
        assertEquals(P3.mapToLength(7), 73);
    }

    @Test
    public void testX() throws Exception {
        assertEquals(0, P1.X(), 0);
        assertEquals(10, P2.X(), 0);
        assertEquals(3, P3.X(), 0);
    }

    @Test
    public void testY() throws Exception {
        assertEquals(0, P1.Y(), 0);
        assertEquals(3, P2.Y(), 0);
        assertEquals(10, P3.Y(), 0);
    }

    @Test
    public void testNextPositions() throws Exception {
        Solve s = new Solve(Helpers.produceList(DataDefs.M1));
        String result = "";
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                result += (s.nextPositions(new Position(x, y)));
            }
        }
        assertTrue(result.equals("[(0,1)]" +
                "[(1,1)]" +
                "[(0,1)]" +
                "[]" +
                "[(1,1)]" +
                "[(0,1), (2,1)]" +
                "[(2,2), (1,1)]" +
                "[]" +
                "[(2,1)]" +
                "[(1,1), (2,2)]" +
                "[(3,2), (2,1)]" +
                "[(2,2)]" +
                "[]" +
                "[(2,1), (3,2)]" +
                "[(2,2)]" +
                "[(3,2)]"));
    }

    @Test
    public void testAllPossible() throws Exception {
        Solve s = new Solve(Helpers.produceList(DataDefs.M1));
        String result = "";
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                result += (s.allPossible(new Position(x, y)));
            }
        }
        assertTrue(result.equals("[(1,0), (0,1)][(1,1), (0,0), (0,2)]" +
                "[(1,2), (0,1), (0,3)]" +
                "[(1,3), (0,2)][(0,0), (2,0), (1,1)]" +
                "[(0,1), (2,1), (1,0), (1,2)]" +
                "[(0,2), (2,2), (1,1), (1,3)]" +
                "[(0,3), (2,3), (1,2)]" +
                "[(1,0), (3,0), (2,1)]" +
                "[(1,1), (3,1), (2,0), (2,2)]" +
                "[(1,2), (3,2), (2,1), (2,3)]" +
                "[(1,3), (3,3), (2,2)]" +
                "[(2,0), (3,1)]" +
                "[(2,1), (3,0), (3,2)]" +
                "[(2,2), (3,1), (3,3)]" +
                "[(2,3), (3,2)]"));
    }

}