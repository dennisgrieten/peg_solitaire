package PegSolitaire.model;

import java.util.Stack;

/**
 * Created by dennis on 28/01/15.
 */
public class Ball {
    private Coordinate history; //

    public void pushHistory(Coordinate c) {
        history = c;
    }

    public Coordinate popHistory() {
        return history;
    }
}
