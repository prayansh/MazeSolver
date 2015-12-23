package Solver;

import Data.DataDefs;
import Data.Helpers;
import Data.Position;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Prayansh on 2015-12-15.
 * Implementation of the solve maze function
 * Solves the maze using
 * - recursive
 * - for loops
 */
public class Solve {
    ArrayList<DataDefs.Cell> MAZE = null;
    int LENGTH = 0;

    public Solve() {
        MAZE = null;
        LENGTH = 0;
    }

    public Solve(ArrayList<DataDefs.Cell> maze) {
        MAZE = maze;
        LENGTH = Helpers.MazeLength(maze);
    }

    public ArrayList<DataDefs.Cell> getMAZE() throws Exception {
        if (MAZE != null)
            return MAZE;
        throw new NullPointerException("Maze is null, cant be produced");
    }

    private boolean checkPass() throws Exception {
        if (MAZE != null)
            return true;
        throw new NullPointerException("Maze is null");
    }

    public void setMAZE(ArrayList<DataDefs.Cell> maze) {
        this.MAZE = maze;
        this.LENGTH = Helpers.MazeLength(maze);
    }

    /**
     * This is a worklist entry which is used to solve the maze
     */
    private class WLE {
        private Position current; //represents the current position on the maze
        private ArrayList<Position> rsf; //represents the Positions that lead to the current position

        public WLE(Position current, ArrayList<Position> rsf) {
            this.current = current;
            this.rsf = rsf;
        }

        @Override
        public String toString() {
            return "{Cur:" + current + "," + rsf + "}";
        }

        public boolean add(Position pos) {
            return rsf.add(pos);
        }

        public Position getCurrent() {
            return current;
        }

        public ArrayList<Position> getRsf() {
            return rsf;
        }
    }

    // ============= IMPLEMENTATION 1 ===================== //
    // ================= Recursion ===================== //
    private boolean validCell(DataDefs.Cell c) {
        switch (c) {
            case X:
                return true;
            case O:
                return false;
            case G:
                return false;
        }
        return false;
    }

    /**
     * Tail Recursive Implementation of Maze
     *
     * @param start
     * @return
     */
    public ArrayList<Position> solveWithRecursion(Position start) {
        try {
            if (checkPass())
                return solvePosition(start, new ArrayList<Position>(),
                        new ArrayList<WLE>(),
                        new ArrayList<Position>());
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Maze not Solvable!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Maze is not initialised");
        }
        return null;
    }

    private ArrayList<Position> solvePosition(Position p, ArrayList<Position> rsf,
                                              ArrayList<WLE> todo, ArrayList<Position> visited) {
        if (solvedQ(p)) {
            rsf.add(p);
            return rsf;
        } else if (visited.contains(p)) {
//            System.out.println("Been Here Done That " + p);
            return solveList(todo, visited);
        } else {
            System.out.println(rsf + "-->" + p + "--> {" + visited + "}");
            ArrayList<Position> newRsf = new ArrayList<Position>(rsf) {{
                add(p);
            }};
            // because it is reference object u need to create a new reference each time
            visited.add(p);
            // This ensures that we follow the current path and not jump from one path to another
            ArrayList<WLE> newTodo = nextPositions(p, newRsf);
            newTodo.addAll(todo);
            return solveList(newTodo, visited);
        }

    }

    private ArrayList<Position> solveList(ArrayList<WLE> todo, ArrayList<Position> visited) {
        if (todo.size() == 0) {
            System.out.println("Not Solvable");
            return null;
        } else {
            return solveEntry(todo.get(0), new ArrayList<>(todo.subList(1, todo.size())), visited);
        }
    }

    private ArrayList<Position> solveEntry(WLE e, ArrayList<WLE> todo, ArrayList<Position> visited) {
        return solvePosition(e.getCurrent(), e.getRsf(), todo, visited);
    }

    /**
     * Produces the next moves by filtering out all invalid moves
     *
     * @param pos
     * @param rsf
     * @return
     */
    public ArrayList<WLE> nextPositions(Position pos, ArrayList<Position> rsf) {
        ArrayList<Position> allPossible = allPossible(pos);
        allPossible.removeIf(p -> validCell(cellAt(p)));
        ArrayList<WLE> result = new ArrayList<>();
        for (Position p : allPossible) {
            result.add(new WLE(p, rsf));
        }
        return result;
    }

    /**
     * Produces the next moves by filtering out all invalid moves
     *
     * @param pos
     * @return
     */
    public ArrayList<Position> nextPositions(Position pos) {
        ArrayList<Position> allPossible = allPossible(pos);
        allPossible.removeIf(p -> validCell(cellAt(p)));
        return allPossible;
    }

    /**
     * Produces all the possible moves from current position
     * that are not out of bound
     *
     * @param pos
     * @return
     */
    public ArrayList<Position> allPossible(Position pos) {
        int x = pos.X();
        int y = pos.Y();

        ArrayList<Position> result = new ArrayList<>();
        result.add(new Position(x - 1, y));
        result.add(new Position(x + 1, y));
        result.add(new Position(x, y - 1));
        result.add(new Position(x, y + 1));

        result.removeIf(position ->
                (position.X() < 0) || (position.Y() < 0)
                        || (position.X() >= LENGTH) || (position.Y() >= LENGTH));
        return result;
    }

    private boolean solvedQ(Position p) {
        return (cellAt(p).getValue() == '$');
    }

    private DataDefs.Cell cellAt(Position p) {
        return MAZE.get(p.mapToLength(LENGTH));
    }


    // ============= IMPLEMENTATION 2 ===================== //
    // TODO Implementation using non-recursive calls i.e while loop
}
