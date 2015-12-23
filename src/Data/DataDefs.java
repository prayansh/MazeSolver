package Data;

/**
 * Created by Prayansh on 2015-12-15.
 * Contains Data Definitions to be used and their interpretations
 * Contains tests of some commonly used helper functions
 */

@SuppressWarnings("unused")
public class DataDefs {

    public enum Cell {
        X('X'),// represents a wall square
        O('_'),// represents an open square
        G('$');// represents the goal square

        private char value;

        Cell(char s) {
            this.value = s;
        }

        public char getValue() {
            return value;
        }

    }

    static Cell X = Cell.X;
    static Cell O = Cell.O;
    static Cell G = Cell.G;

    // Maze is Cell[][] of length N, where N is a perfect square
    // we will interp. maze as a straight line however the positions will be x,y
    // and then mapped as length using defined functions
    /* Add Test for each board to check if valid */
    // TODO implement Maze as an extension of List<Cell> for more readability
    // TODO implement Path as an extension of List<Position>

    public static Cell M0[][] = {
            {X, X, X},
            {O, O, O},
            {X, X, G}};
    public static Position P0 = new Position(0, 1);

    public static Cell M1[][] = {
            {X, X, X, X},
            {O, O, O, X},
            {X, X, O, G},
            {X, X, X, X}};
    public static Position P1 = new Position(0, 1);

    public static Cell M2[][] = {
            {X, X, X, X},
            {O, O, X, X},
            {X, X, O, G},
            {X, X, X, X}};
    public static Position P2 = new Position(0, 1);

    public static Cell M3[][] = {
            {X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, O, O, O, X},
            {X, X, X, X, O, X, X, O, X},
            {X, O, O, O, O, X, X, G, X},
            {X, X, X, X, O, X, X, O, X},
            {X, X, X, X, O, X, X, O, X},
            {X, X, X, X, O, X, X, O, X},
            {X, X, X, X, O, O, O, O, X},
            {X, X, X, X, X, X, X, X, X}};
    public static Position P3 = new Position(1, 3);

    public static Cell M4[][] = {
            {X, X, X, X, X, X, X, X, X, X},
            {X, X, O, O, O, O, O, X, O, X},
            {X, O, O, X, X, X, O, X, O, X},
            {X, O, X, X, O, O, O, O, O, X},
            {X, O, X, X, O, X, X, X, O, X},
            {X, O, X, O, O, X, O, O, O, X},
            {X, O, O, O, X, X, O, X, X, X},
            {X, X, X, X, X, X, O, X, X, X},
            {X, O, O, O, O, O, O, O, G, X},
            {X, X, X, X, X, X, X, X, X, X}};
    public static Position P4 = new Position(1, 2);

    public static Cell M5[][] = {
            {X, O, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, O, X, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, X},
            {X, O, X, O, X, X, X, O, X, O, X, X, X, X, X, X, X, X, X},
            {X, O, O, O, X, O, O, O, X, O, O, O, O, O, O, O, O, O, X},
            {X, O, X, X, X, O, X, X, X, X, X, X, X, X, X, O, X, O, X},
            {X, O, X, O, O, O, X, O, O, O, O, O, O, O, X, O, X, O, X},
            {X, O, X, X, X, X, X, O, X, X, X, X, X, O, X, X, X, O, X},
            {X, O, O, O, X, O, O, O, O, O, O, O, X, O, X, O, O, O, X},
            {X, X, X, O, X, O, X, X, X, X, X, O, X, O, X, O, X, O, X},
            {X, O, O, O, X, O, X, O, O, O, X, O, X, O, X, O, X, O, X},
            {X, X, X, O, X, O, X, O, X, O, X, O, X, O, X, O, X, O, X},
            {X, O, O, O, X, O, O, O, X, O, X, O, X, O, O, O, X, O, X},
            {X, O, X, X, X, X, X, X, X, O, X, X, X, X, X, X, X, X, X},
            {X, O, O, O, O, O, O, O, X, O, X, O, O, O, O, O, O, O, X},
            {X, X, X, X, X, O, X, O, X, O, X, O, X, X, X, X, X, O, X},
            {X, O, O, O, X, O, X, O, X, O, O, O, X, O, O, O, X, O, X},
            {X, O, X, X, X, O, X, O, X, X, X, X, X, O, X, O, X, O, X},
            {X, O, O, O, O, O, X, O, O, O, O, O, O, O, X, O, X, O, G},
            {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}};
    public static Position P5 = new Position(1, 0);


    // Invalid Maze
    public static Cell M6[][] = {
            {X, X, X},
            {O, O, O},
            {X, X, G},
            {X, X, X}};
}
