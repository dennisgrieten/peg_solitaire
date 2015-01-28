package PegSolitaire;

/**
 * Created by dennis on 28/01/15.
 */
public class Coordinate {
    private byte x;
    private byte y;

    public Coordinate(byte x, byte y) {
        this.x = x;
        this.y = y;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }
}
