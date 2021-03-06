package Data;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Prayansh on 2015-12-15.
 */
public class Helpers {
    // ======= HELPER FUNCTIONS ========

    /**
     * Transforms the Cell[][] into List<Cell>
     * Also length of a side is sqrt(maze.size())
     */
    public static ArrayList<DataDefs.Cell> produceList(DataDefs.Cell maze[][]) throws Exception {
        ArrayList<DataDefs.Cell> result = new ArrayList<>();
        if (!validMaze(maze))
            throw new Exception("Maze is not a perfect square");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                result.add(maze[i][j]);
            }
        }
        return result;
    }

    public static int MazeLength(ArrayList<DataDefs.Cell> maze) {
        return (int) Math.sqrt(maze.size());
    }

    /**
     * Renders the Image of the Maze
     */

    public static void renderMaze(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Renders the Image of the Maze
     */

    public static void renderMaze(DataDefs.Cell[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j].getValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void renderMaze(ArrayList<DataDefs.Cell> maze) {
        int len = MazeLength(maze);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(maze.get(new Position(j, i).mapToLength(len)).getValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void renderMazeWithPath(ArrayList<DataDefs.Cell> maze, ArrayList<Position> path) {
        int len = MazeLength(maze);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                Position cur = new Position(j, i);
                if(cur.equals(path.get(0)))
                    System.out.print("π" + " ");
                else if (maze.get(cur.mapToLength(len)).equals(DataDefs.Cell.G))
                    System.out.print("" + " ");
                else if (path.contains(cur))
                    System.out.print("•" + " ");
                else
                    System.out.print(maze.get(cur.mapToLength(len)).getValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    /**
     * Tests to check if mazes are valid
     * Maze is valid if length of maze s perfect square
     */

    @Test
    public void testMaze() {
        assertTrue(validMaze(DataDefs.M1));
        assertTrue(validMaze(DataDefs.M2));
        assertTrue(validMaze(DataDefs.M3));
        assertTrue(validMaze(DataDefs.M4));
        assertTrue(validMaze(DataDefs.M5));
        assertFalse(validMaze(DataDefs.M6));
    }

    public static boolean validMaze(DataDefs.Cell[][] maze) {
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

    public static boolean perfectSquare(int n) {
        double sqrt = Math.sqrt(n);
        double diff = sqrt - Math.floor(sqrt);
        return !(diff > 0);
    }
}
