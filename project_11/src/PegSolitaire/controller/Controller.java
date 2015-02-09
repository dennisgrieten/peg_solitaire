package PegSolitaire.controller;

import PegSolitaire.model.Field;
import java.lang.String;

/**
 * Created by dennis on 28/01/15.
 */
public class Controller {
    private Field field = new Field(7, 7);

    /* Doe een zet */
    public void doMove(int x0, int y0, int x1, int y1) {
        field.moveBall(x0, y0, x1, y1);
    }

    /* Maak een zet ongedaan */
    public void undoMove() {
        field.undoMove();
    }

    @Override
    public String toString() {
        return field.toString();
    }
}
