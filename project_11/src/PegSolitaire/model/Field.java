package PegSolitaire.model;

import java.util.Stack;

/**
 * Created by dennis on 28/01/15.
 */
public class Field {
    private Hole[][] matrix;
    private Stack<Ball> stack = new Stack<Ball>();  // Stack voor verwijderde ballen
    private Stack<Coordinate> moveHistory = new Stack<Coordinate>();
    private byte[] deadZoneMap = new byte[]{0, 1, 5, 6};

    public Field(int dimensionX, int dimensionY) {
        matrix = new Hole[dimensionY][dimensionX];
        initField();
    }

    /* Maak matrix aan */
    private void initField() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Hole(new Ball(), new Coordinate(i,j), this);
            }
        }

        /* Vlag onbruikbare velden */
        for (byte i : deadZoneMap) {
            for (byte j : deadZoneMap) {
                matrix[i][j].setDeadZone(true);
            }
        }

        matrix[3][3].clearBall();   // Delete middelste bal
    }

    private void pushBall(Coordinate c) {
        stack.push(matrix[c.x()][c.y()].giveBall(true));  // Verplaats bal van opgegeven vak naar de stack
    }

    public void moveBall(int x, int y, int x1, int y1) {
        if (inField(x, y) || inField(x1, y1)) {
            matrix[x1][y1].setBall(matrix[x][y].giveBall());    // Overhandig bal van één vak naar het ander
            pushBall(getVector(x, y, x1, y1));                  // Verwijder bal
            moveHistory.push(new Coordinate(x, y));             // Plaats coörrdinaat zet beginvak in geschiedenis
            moveHistory.push(new Coordinate(x1, y1));           // Plaats coördinaat zet eindvak in geschiedinis
        }
    }

    public void undoMove() {
        if (stack.size() != 0) {
            resetBall(stack.pop());
            Coordinate a =  moveHistory.pop();
            Coordinate b =  moveHistory.pop();
            matrix[b.x()][b.y()].setBall(matrix[a.x()][a.y()].giveBall());
        }
    }

    private void resetBall(Ball b) {
        Coordinate c = b.popHistory();                   // Pop coördinaat van bal geschiedenis in tijdelijke pointer
        matrix[c.x()][c.y()].setBall(b);              // Zet bal terug op het veld met oude coördinaten
    }

    /* Controlleer of de gegeven coördinaten in het veld en buiten de dode zone liggen */
    private boolean inField(int x, int y) {
        return x <= matrix[y].length ? (matrix[y][x].isDeadZone() ? false : true) : false;
    }

    /* Bereken in welke richting de zet gedaan werd om de juiste bal weg te nemen */
    private Coordinate getVector(int x, int y, int x1, int y1) {
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

        output.append("  0 1 2 3 4 5 6\n");
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
