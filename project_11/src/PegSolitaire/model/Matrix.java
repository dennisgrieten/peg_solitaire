package PegSolitaire.model;

import java.util.Stack;

/**
 * Created by dennis on 28/01/15.
 */
public class Matrix {
    Hole[][] matrix = new Hole[7][7];
    Stack<Ball> stack = new Stack<Ball>();  // Stack voor verwijderde ballen

    public Matrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Hole(new Ball());
            }
        }
        matrix[3][3].clearBall();   // Delete middelste bal
    }
}
