package Data;

/**
 * Created by Prayansh on 2015-12-15.
 */

public class Position {
    private int x, y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position mapUsingLength(int l, int wrap) {
        if (wrap == 0)
            return new Position();
        return new Position(l % wrap, l / wrap);
    }

    public int mapToLength(int wrap) {
        return (this.y * wrap) + this.x;
    }

    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof Position)
                && (x == ((Position) obj).X())
                && (y == ((Position) obj).Y()));
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
