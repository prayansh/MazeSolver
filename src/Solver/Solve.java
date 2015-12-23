package Solver;

import Data.DataDefs;
import Data.Helpers;
import Data.Position;

import java.util.ArrayList;

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

    public void clear() {
        MAZE = null;
        LENGTH = 0;
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
                return solvePosition(start,
                        new ArrayList<Position>(),//rsf
                        new ArrayList<WLE>(),// work-list
                        new ArrayList<Position>());//visited
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
            todo.clear();
            visited.clear();
            return rsf;
        } else if (visited.contains(p)) {
//            System.out.println("Been Here Done That " + p);
            return solveList(todo, visited);
        } else {
//            System.out.println(rsf + "-->" + p + "--> {" + visited + "}");
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

    /**
     * Loop Implementation of Solver
     *
     * @param pos
     * @return
     */
    public ArrayList<Position> solveWithLoop(Position pos) {
        ArrayList<Position> rsf = new ArrayList<>();
        ArrayList<Position> visited = new ArrayList<>();
        ArrayList<WLE> wle = new ArrayList<>();
        wle.add(new WLE(pos, rsf));
        while (!wle.isEmpty()) {
            WLE now = wle.get(0);
            if (solvedQ(now.getCurrent())) {
                now.getRsf().add(now.getCurrent());
                return now.getRsf();
            } else if (visited.contains(now.getCurrent())) {
                wle.remove(now);
                continue;
            } else {
                visited.add(now.getCurrent());
                ArrayList<Position> newRsf = new ArrayList<Position>(now.getRsf()) {{
                    add(now.getCurrent());
                }};
                ArrayList<WLE> newTodo = nextPositions(now.getCurrent(), newRsf);
                wle.remove(now);
                wle = append(newTodo, wle);
                continue;
            }
        }
        return null;
    }

    /**
     * Appends l1 to l2 with l1 being first
     * !!!! TESTED DO NOT CHANGE !!!!
     *
     * @param l1
     * @param l2
     * @return (append (list 1) (list 2)) -> (list 1 2)
     */
    public static ArrayList<WLE> append(ArrayList<WLE> l1, ArrayList<WLE> l2) {
        for (WLE a : l2) {
            l1.add(a);
        }
        l2.clear();
        return l1;
    }

    //TODO Generate all paths
    //TODO Generate a solvable maze
}
