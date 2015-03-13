package PegSolitaire.dom.field;

import java.io.Serializable;

/**
 * Created by dennis on 28/01/15.
 */
public class Peg implements Serializable{
    private Coordinate history; //

    public void pushHistory(Coordinate c) {
        history = c;
    }

    public Coordinate popHistory() {
        return history;
    }
}
