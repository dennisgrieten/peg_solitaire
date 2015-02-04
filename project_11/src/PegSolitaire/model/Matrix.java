package PegSolitaire.model;

import java.util.Stack;

/**
 * Created by dennis on 28/01/15.
 */
public class Matrix {
    private Hole[][] matrix = new Hole[7][7];
    private Stack<Ball> stack = new java.util.Stack<Ball>();  // Stack voor verwijderde ballen
    private byte[] deadZoneMap = new byte[]{0, 1, 5, 6};

    public Matrix() {
        initField();
    }

    // Maak matrix aan
    private void initField() {
        for (byte i = 0; i < matrix.length; i++) {
            for (byte j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Hole(new Ball(), new Coordinate(i,j));
            }
        }

        // Vlag onbruikbare velden
        for (byte i : deadZoneMap) {
            for (byte j : deadZoneMap) {
                matrix[i][j].setDeadZone(true);
            }
        }

        matrix[3][3].clearBall();   // Delete middelste bal
    }

    // Verplaats bal van één vak naar het ander opgegeven vak
    public void moveBall(int x, int y, int x1, int y1){
        matrix[x1][y1].setBall(matrix[x][y].giveBall());
        removeBall(calculateVector(x, y, x1, y1));
    }

    private void removeBall(Coordinate c) {
        stack.push(matrix[c.getX()][c.getY()].giveBall());
    }

    // Bereken in welke richting de zet gedaan wordt om de juiste bal weg te nemen
    private Coordinate calculateVector(int x, int y, int x1, int y1) {
        if (x == x1) {
            if (y < y1) {
                return new Coordinate(x, y + 1);
            } else {
                return new Coordinate(x, y - 1);
            }
        } else {
            if (x < x1) {
                return new Coordinate(x + 1, y);
            } else {
                return new Coordinate(x - 1, y);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("#Peg Solitaire#\n  0 1 2 3 4 5 6\n");
        for (int i = 0; i < matrix.length; i++) {
            output.append(i + " ");
            for (int j = 0; j < matrix[i].length; j++) {
                output.append(matrix[j][i].toString()).append(" ");
            }
            output.append("\n");
        }

        return output.toString();
    }
}
