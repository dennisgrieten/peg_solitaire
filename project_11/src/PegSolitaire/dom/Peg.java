package PegSolitaire.dom;

/**
 * Created by dennis on 28/01/15.
 */
public class Peg {
    private Coordinate history; //

    public void pushHistory(Coordinate c) {
        history = c;
    }

    public Coordinate popHistory() {
        return history;
    }
}
