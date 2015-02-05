package PegSolitaire.controller;

import PegSolitaire.model.Matrix;

/**
 * Created by dennis on 28/01/15.
 */
public class Controller {
    private Matrix matrix;

    /* Doe een zet */
    public void doMove(int x0, int y0, int x1, int y1) {
        matrix.moveBall(x0, y0, x1, y1);
    }

    /* Maak een zet ongedaan */
    public void undoMove() {
        // Nog te implementeren
    }
}
