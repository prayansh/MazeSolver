package Generator;

import Data.DataDefs;
import Data.Position;

import java.util.ArrayList;

/**
 * Created by Prayansh on 2015-12-23.
 * http://www.jamisbuck.org/presentations/rubyconf2011/index.html#binary-tree
 */
public class BinaryTree {
    private final int NE = 0;
    private final int NW = 1;
    private final int SE = 2;
    private final int SW = 3;

    private final int DIR = (int) (Math.random() * 4);
    private int SIZE;

    public DataDefs.Cell[][] maze;
    public Position start;

    public BinaryTree() {
        SIZE = 5;
        setupMaze();
    }

    public BinaryTree(int size) {
        SIZE = size;
        setupMaze();
    }

    private void setupMaze() {
        maze = new DataDefs.Cell[SIZE][SIZE];
        //Fill with Walls
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                maze[i][j] = DataDefs.Cell.X;
            }
        }
    }

    public Position getStart() {
        return start;
    }

    public DataDefs.Cell[][] getMaze() {
        return maze;
    }

    public void makeMaze() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                ArrayList<Position> allPos = allPossible(new Position(j, i));
                if (allPos.size() == 0)
                    continue;
                int index = (int) (Math.random() * allPos.size());
                Position next = allPos.get(index);
                maze[next.X()][next.Y()] = DataDefs.Cell.O;
            }
        }/*
        outer:
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (maze[j][i] == DataDefs.Cell.O) {
                    start = new Position(j, i);
                    break outer;
                }
            }
        }
        outer:
        for (int i = SIZE - 1; i >= 0; i--) {
            for (int j = SIZE - 1; j >= 0; j--) {
                if (maze[j][i] == DataDefs.Cell.O) {
                    maze[j][i] = DataDefs.Cell.G;
                    break outer;
                }
            }
        }*/
    }

    private ArrayList<Position> allPossible(Position pos) {
        int x = pos.X();
        int y = pos.Y();

        ArrayList<Position> result = new ArrayList<>();

        switch (DIR) {
            case NE:
                result.add(new Position(x, y - 1));// Up
                result.add(new Position(x + 1, y));// Right
                break;
            case NW:
                result.add(new Position(x, y - 1));// Up
                result.add(new Position(x - 1, y));// Left
                break;
            case SE:
                result.add(new Position(x, y + 1));// Down
                result.add(new Position(x + 1, y));// Right
                break;
            case SW:
                result.add(new Position(x, y + 1));// Down
                result.add(new Position(x - 1, y));// Left
                break;
        }

        result.removeIf(position ->
                (position.X() < 0) || (position.Y() < 0)
                        || (position.X() >= SIZE) || (position.Y() >= SIZE));
        return result;
    }
}
