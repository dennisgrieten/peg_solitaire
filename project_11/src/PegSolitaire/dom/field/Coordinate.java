package PegSolitaire.dom.field;

import java.io.Serializable;

/**
 * Created by dennis on 28/01/15.
 */
public class Coordinate implements Serializable {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
