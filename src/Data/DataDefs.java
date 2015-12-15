package Data;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Prayansh on 2015-12-15.
 * Contains Data Definitions to be used and their interpretations
 * Contains tests of some commonly used helper functions
 * TODO come up with an idea to use List<Cell> instead of double-d array
 */

@SuppressWarnings("unused")
public class DataDefs {

    public enum Cell {
        X("X"),// represents a wall square
        O("_"),// represents an open square
        G("$");// represents the goal square

        private String value;

        Cell(String s) {
            this.value = s;
        }

        public String getValue() {
            return value;
        }

    }

    //TODO Refactor name of open cell
    // _ -> Provides visualizing the problem
    Cell X = Cell.X;
    Cell _ = Cell.O;
    Cell G = Cell.G;

    // Maze is Cell[][] of length N, where N is a perfect square
    // we will interp. maze as a straight line however the positions will be x,y
    // and then mapped as length using defined functions
    /* Add Test for each board to check if valid */

    Cell M1[][] = {
            {X, X, X, X},
            {_, _, _, X},
            {X, X, _, G},
            {X, X, X, X}};

    Cell M2[][] = {
            {X, X, X, X},
            {_, _, X, X},
            {X, X, _, G},
            {X, X, X, X}};

    Cell M3[][] = {
            {X, X, X, X, X, X, X, X, X},
            {X, X, _, _, _, _, _, _, X},
            {X, X, X, X, _, X, X, _, X},
            {X, _, _, _, _, X, X, G, X},
            {X, X, X, X, _, X, X, _, X},
            {X, X, X, X, _, X, X, _, X},
            {X, X, X, X, _, X, X, _, X},
            {X, X, X, X, _, _, _, _, X},
            {X, X, X, X, X, X, X, X, X}};

    Cell M4[][] = {
            {X, X, X, X, X, X, X, X, X, X},
            {X, X, _, _, _, _, _, X, _, X},
            {X, _, _, X, X, X, _, X, _, X},
            {X, _, X, X, _, _, _, _, _, X},
            {X, _, X, X, _, X, X, X, _, X},
            {X, _, X, _, _, X, _, _, _, X},
            {X, _, _, _, X, X, _, X, X, X},
            {X, X, X, X, X, X, _, X, X, X},
            {X, _, _, _, _, _, _, _, G, X},
            {X, X, X, X, X, X, X, X, X, X}};

    Cell M5[][] = {
            {X, _, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, _, X, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, X},
            {X, _, X, _, X, X, X, _, X, _, X, X, X, X, X, X, X, X, X},
            {X, _, _, _, X, _, _, _, X, _, _, _, _, _, _, _, _, _, X},
            {X, _, X, X, X, _, X, X, X, X, X, X, X, X, X, _, X, _, X},
            {X, _, X, _, _, _, X, _, _, _, _, _, _, _, X, _, X, _, X},
            {X, _, X, X, X, X, X, _, X, X, X, X, X, _, X, X, X, _, X},
            {X, _, _, _, X, _, _, _, _, _, _, _, X, _, X, _, _, _, X},
            {X, X, X, _, X, _, X, X, X, X, X, _, X, _, X, _, X, _, X},
            {X, _, _, _, X, _, X, _, _, _, X, _, X, _, X, _, X, _, X},
            {X, X, X, _, X, _, X, _, X, _, X, _, X, _, X, _, X, _, X},
            {X, _, _, _, X, _, _, _, X, _, X, _, X, _, _, _, X, _, X},
            {X, _, X, X, X, X, X, X, X, _, X, X, X, X, X, X, X, X, X},
            {X, _, _, _, _, _, _, _, X, _, X, _, _, _, _, _, _, _, X},
            {X, X, X, X, X, _, X, _, X, _, X, _, X, X, X, X, X, _, X},
            {X, _, _, _, X, _, X, _, X, _, _, _, X, _, _, _, X, _, X},
            {X, _, X, X, X, _, X, _, X, X, X, X, X, _, X, _, X, _, X},
            {X, _, _, _, _, _, X, _, _, _, _, _, _, _, X, _, X, _, G},
            {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}};

    Cell M6[][] = {
            {X, X, X},
            {_, _, _},
            {X, X, G},
            {X, X, X}};

    // ======= HELPER FUNCTIONS ========

    /**
     * Transforms the Cell[][] into List<Cell>
     * Also length of a side is sqrt(maze.size())
     * TODO
     */

    /**
     * Renders the Image of the Maze
     */

    public void renderMaze(Cell[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.println(maze[i][j].getValue());
            }
        }
    }


    /**
     * Tests to check if mazes are valid
     * Maze is valid if length of maze s perfect square
     */

    @Test
    public void testMaze() {
        assertTrue(validMaze(M1));
        assertTrue(validMaze(M2));
        assertTrue(validMaze(M3));
        assertTrue(validMaze(M4));
        assertTrue(validMaze(M5));
        assertFalse(validMaze(M6));
    }

    public boolean validMaze(Cell[][] maze) {
        return perfectSquare(maze.length * maze[0].length);
    }


    /**
     * Test for PerfectSquare Function
     */
    @Test
    public void testPerfectSquare() {
        assertTrue(perfectSquare(1));
        assertFalse(perfectSquare(2));
        assertFalse(perfectSquare(3));
        assertFalse(perfectSquare(10));
        assertFalse(perfectSquare(21));
        assertTrue(perfectSquare(36));
    }

    boolean perfectSquare(int n) {
        double sqrt = Math.sqrt(n);
        double diff = sqrt - Math.floor(sqrt);
        return !(diff > 0);
    }

}
