package PegSolitaire.controller;

import PegSolitaire.exception.IllegalCoordinateException;
import PegSolitaire.exception.IllegalMoveException;
import PegSolitaire.model.Field;
import java.lang.String;

/**
 * Created by dennis on 28/01/15.
 */
public class Controller {
    private Field field = new Field(7, 7);

    /* Doe een zet */
    public void doMove(int x, int y, int x1, int y1) {
        try {
            field.doMove(x, y, x1, y1);
        } catch (IllegalCoordinateException e) {
            System.out.println(e.getMessage());
        } catch (IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
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
