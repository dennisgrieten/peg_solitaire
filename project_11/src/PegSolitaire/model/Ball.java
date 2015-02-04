package PegSolitaire.model;

import java.util.Stack;

/**
 * Created by dennis on 28/01/15.
 */
public class Ball {
    // Stack voor de geschiedenis van 'x' en 'y' coördinaten
    private Stack<Coordinate> history = new Stack<Coordinate>();

    // Coördinaat op de history stack pushen
    public void pushCoordinate(Coordinate c) {
        history.push(c);
    }

    public Coordinate popCoordinate() {
        return history.pop();
    }
}
